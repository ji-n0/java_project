package com.example.bybitAutoTrade.repository;

import com.example.bybitAutoTrade.entity.Mbr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MbrRepository extends JpaRepository<Mbr, String> {

    @Query("SELECT m.id FROM Mbr m")
    List<String> findAllMbrIds();
}
