package com.helmes.form.dao;

import com.helmes.form.model.Sector;

import java.util.List;

public interface SectorDaoInterface {

    void save(Sector sector);

    List<Sector> findAll();

    Sector findSectorById(Integer id);

    void update(Sector sector);

    List<Sector> findAllPersonSectors(Integer id);
}
