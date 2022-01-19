package com.dashboard.interfaces;

import com.dashboard.model.Repository.OverallModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOverallRepository extends JpaRepository<OverallModel, Integer> {
    Optional<OverallModel> findByDate(String date);

}
