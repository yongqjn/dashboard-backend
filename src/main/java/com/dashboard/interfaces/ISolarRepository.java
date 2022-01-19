package com.dashboard.interfaces;

import com.dashboard.model.Repository.SolarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolarRepository extends JpaRepository<SolarModel, Integer> {
    List<SolarModel> findByDate(String date);
}
