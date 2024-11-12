# 1. 경량의 OpenJDK 이미지를 기반으로 선택
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. JAR 파일 복사
COPY build/libs/bybitAutoTrade-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행 명령 설정
ENTRYPOINT ["java", "-jar", "app.jar"]
