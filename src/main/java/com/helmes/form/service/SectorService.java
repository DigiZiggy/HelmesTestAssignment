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
    public Sector findSectorById(Integer id) {
        return sectorDao.findSectorById(id);
    }


    @Override
    public List<Sector> findAll() {
        return sectorDao.findAll();
    }

    @Override
    public List<Sector> findAllPersonSectors(Integer id) {
        return sectorDao.findAllPersonSectors(id);
    }

    @Override
    public void saveOrUpdate(Sector sector) {
        if (sector.getId() == null) {
            sectorDao.save(sector);
        } else {
            sectorDao.update(sector);
        }
    }
}
