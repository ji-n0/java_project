import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseConnector {

    private String url;
    private String username;
    private String password;

    public DatabaseConnector() {
        loadConfig();
    }

    // YAML 설정 로드 메서드
    private void loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.yml")) {
            Map<String, Object> config = yaml.load(inputStream);
            Map<String, Object> dbConfig = (Map<String, Object>) config.get("database");
            url = (String) dbConfig.get("url");
            username = (String) dbConfig.get("username");
            password = (String) dbConfig.get("password");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    // 데이터베이스 연결 메서드
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // SELECT 쿼리 실행 메서드
    public List<Map<String, Object>> executeSelectQuery(String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new java.util.HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                resultList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
