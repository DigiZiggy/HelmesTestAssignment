package com.helmes.form.service;

import com.helmes.form.model.Sector;

import java.util.List;

public interface SectorServiceInterface {

    Sector findSectorById(Integer id);

    List<Sector> findAll();

    void saveOrUpdate(Sector sector);

    List<Sector> findAllPersonSectors(Integer id);
}
