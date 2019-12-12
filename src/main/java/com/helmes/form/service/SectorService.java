package com.helmes.form.service;

import com.helmes.form.dao.SectorDaoInterface;
import com.helmes.form.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService implements SectorServiceInterface {

    SectorDaoInterface sectorDao;

    @Autowired
    public void setSectorDao(SectorDaoInterface sectorDao) {
        this.sectorDao = sectorDao;
    }

    @Override
    public List<Sector> findAll() {
        return sectorDao.findAll();
    }
}
