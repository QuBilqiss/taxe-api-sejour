/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.taxesejourv1.serviceImpl;

import com.sir.taxesejourv1.bean.TauxTaxeSejour;
import com.sir.taxesejourv1.dao.TauxTaxeSejourDao;
import com.sir.taxesejourv1.service.TauxTaxeSejourService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jawadoo
 */
@Service
public class TauxTaxeSejourServiceImpl implements TauxTaxeSejourService {

    @Autowired
    private TauxTaxeSejourDao tauxTaxeSejourDao;

    @Override
    public TauxTaxeSejour findByRefCategorie(String refCategorie) {
        return  tauxTaxeSejourDao.findByRefCategorie(refCategorie);
    }

    @Override
    public int saveTauxTaxeSejour(TauxTaxeSejour tauxTaxeSejour) {
        if(tauxTaxeSejour==null ){
            return -1;
        }else{
                tauxTaxeSejourDao.save(tauxTaxeSejour);
            return 1;
        }
    }
    
    
    public TauxTaxeSejourDao getTauxTaxeSejourDao() {
        return tauxTaxeSejourDao;
    }

    public void setTauxTaxeSejourDao(TauxTaxeSejourDao tauxTaxeSejourDao) {
        this.tauxTaxeSejourDao = tauxTaxeSejourDao;
    }

    @Override
    public List<TauxTaxeSejour> findAll() {
        return tauxTaxeSejourDao.findAll();
    }

    @Override
    public void deleteByRefCategorie(String refCategorie) {
        TauxTaxeSejour tauxTaxeSejour= tauxTaxeSejourDao.findByRefCategorie(refCategorie);
            tauxTaxeSejourDao.delete(tauxTaxeSejour);
            
        
            
        }
    }
       