/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.taxesejourv1.rest;

import com.sir.taxesejourv1.bean.TauxTaxeSejour;
import com.sir.taxesejourv1.dao.TauxTaxeSejourDao;
import com.sir.taxesejourv1.rest.converter.AbstractConverter;
import com.sir.taxesejourv1.rest.converter.TauxTaxeSejourConverter;
import com.sir.taxesejourv1.rest.vo.TauxTaxeSejourVo;
import com.sir.taxesejourv1.service.TauxTaxeSejourService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jawadoo
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping({"/taxe-sejour-v1/tauxTaxeSejour"})
public class TauxTaxeSejourRest {
    @Autowired
    private TauxTaxeSejourService tauxTaxeSejourService;
    @Autowired
    private TauxTaxeSejourDao tauxTaxeSejourDao;
    @Autowired
    private TauxTaxeSejourConverter tauxTaxeSejourConverter;
    @Autowired
  @Qualifier("tauxTaxeSejourConverter")
  private AbstractConverter<TauxTaxeSejour,TauxTaxeSejourVo> abstractConverter;

     @PostMapping("/")
    public int saveTauxTaxeSejour(@RequestBody TauxTaxeSejourVo tauxTaxeSejourVo) {
        TauxTaxeSejour tauxTaxeSejour = tauxTaxeSejourConverter.toItem(tauxTaxeSejourVo);
        int res= tauxTaxeSejourService.saveTauxTaxeSejour(tauxTaxeSejour);
         return res;
    }
@GetMapping("/")
    public List<TauxTaxeSejourVo> findAll() {
        return abstractConverter.toVo(tauxTaxeSejourService.findAll());
    }
@DeleteMapping("/delete/{refCategorie}")
    public void deleteByRefCategorie(@PathVariable("refCategorie") String refCategorie) {
        tauxTaxeSejourService.deleteByRefCategorie(refCategorie);
    }
 

    public TauxTaxeSejourDao getTauxTaxeSejourDao() {
        return tauxTaxeSejourDao;
    }

    public void setTauxTaxeSejourDao(TauxTaxeSejourDao tauxTaxeSejourDao) {
        this.tauxTaxeSejourDao = tauxTaxeSejourDao;
    }

    public AbstractConverter<TauxTaxeSejour, TauxTaxeSejourVo> getAbstractConverter() {
        return abstractConverter;
    }

    public void setAbstractConverter(AbstractConverter<TauxTaxeSejour, TauxTaxeSejourVo> abstractConverter) {
        this.abstractConverter = abstractConverter;
    }

     

    public TauxTaxeSejourConverter getTauxTaxeSejourConverter() {
        return tauxTaxeSejourConverter;
    }

    public void setTauxTaxeSejourConverter(TauxTaxeSejourConverter tauxTaxeSejourConverter) {
        this.tauxTaxeSejourConverter = tauxTaxeSejourConverter;
    }
    
    
    
    public TauxTaxeSejourService getTauxTaxeSejourService() {
        return tauxTaxeSejourService;
    }

    public void setTauxTaxeSejourService(TauxTaxeSejourService tauxTaxeSejourService) {
        this.tauxTaxeSejourService = tauxTaxeSejourService;
    }
    
}
