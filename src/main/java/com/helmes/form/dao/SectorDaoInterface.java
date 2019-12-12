package com.helmes.form.dao;

import com.helmes.form.model.Sector;

import java.util.List;

public interface SectorDaoInterface {

    List<Sector> findAll();

    List<Sector> findAllPersonSectors(Integer id);
}
