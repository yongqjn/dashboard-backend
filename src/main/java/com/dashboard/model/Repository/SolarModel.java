package com.dashboard.model.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solar_data")
public class SolarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer index;
    private String date;
    private String time;
    private Float wh;
}
