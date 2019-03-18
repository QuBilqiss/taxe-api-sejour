/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.taxesejourv1.rest;

import com.sir.taxesejourv1.bean.TaxeSejourTrimestrielle;
import com.sir.taxesejourv1.rest.converter.AbstractConverter;
import com.sir.taxesejourv1.rest.converter.TaxeSejourTrimestrielleConverter;
import com.sir.taxesejourv1.rest.exchage.vo.LocalVo;
import com.sir.taxesejourv1.rest.proxy.LocalProxy;
import com.sir.taxesejourv1.rest.vo.TaxeSejourTrimestrielleVo;
import com.sir.taxesejourv1.service.TaxeSejourTrimestrielleService;
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
@RestController()
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping({"/taxe-sejour-v1/taxeSejours"})
public class TaxeSejourTrimestrielleRest {
    @Autowired
    private LocalProxy localProxy;
    @Autowired
    private TaxeSejourTrimestrielleService taxeSejourTrimestrielleService;
    @Autowired
    private TaxeSejourTrimestrielleConverter taxeSejourTrimestrielleConverter;
    @Autowired
  @Qualifier("taxeSejourTrimestrielleConverter")
  private AbstractConverter<TaxeSejourTrimestrielle,TaxeSejourTrimestrielleVo> taAbstractConverter;

    @PostMapping("/")
    public int  creertaxe(@RequestBody TaxeSejourTrimestrielleVo taxesejourTrimestrielleVo) {
        TaxeSejourTrimestrielle taxe = taxeSejourTrimestrielleConverter.toItem(taxesejourTrimestrielleVo);
     int res = taxeSejourTrimestrielleService.creertaxe(taxe);
        return res;
    }

     @DeleteMapping("/delete/{reference}")
    public void deleteByReference(@PathVariable("reference") String reference) {
        taxeSejourTrimestrielleService.deleteByReference(reference);
    }
    
     @GetMapping("/reference/{reference}")
    public TaxeSejourTrimestrielle findByReference( @PathVariable String reference) {
        return taxeSejourTrimestrielleService.findByReference(reference);
    }
    
    @GetMapping("/referenceLocal/{referenceLocal}")
    public List<TaxeSejourTrimestrielle> findByReferenceLocal( @PathVariable String referenceLocal) {
        return taxeSejourTrimestrielleService.findByReferenceLocal(referenceLocal);
    }
   @GetMapping("/annee/{annee}/montantMin/{montantMin}/montantMax/{montantMax}")
    public List<TaxeSejourTrimestrielle> findByCriteria(@PathVariable Integer annee,@PathVariable Double montantMin,@PathVariable Double montantMax) {
        return taxeSejourTrimestrielleService.findByCriteria(annee, montantMin, montantMax);
    }
    
   @GetMapping("/annee/{annee}/numeroTrimester/{numeroTrimester}")
    public List<TaxeSejourTrimestrielle> findByAnneeAndNumeroTrimester(@PathVariable Integer annee,@PathVariable Integer numeroTrimester) {
        return taxeSejourTrimestrielleService.findByAnneeAndNumeroTrimester(annee, numeroTrimester);
    }
    
@GetMapping("/")
    public List<TaxeSejourTrimestrielleVo> findAll() {
        return taAbstractConverter.toVo(taxeSejourTrimestrielleService.findAll());


    }
    
    
    public TaxeSejourTrimestrielleConverter getTaxeSejourTrimestrielleConverter() {
        return taxeSejourTrimestrielleConverter;
    }

    public void setTaxeSejourTrimestrielleConverter(TaxeSejourTrimestrielleConverter taxeSejourTrimestrielleConverter) {
        this.taxeSejourTrimestrielleConverter = taxeSejourTrimestrielleConverter;
    }

    public LocalProxy getLocalProxy() {
        return localProxy;
    }

    public void setLocalProxy(LocalProxy localProxy) {
        this.localProxy = localProxy;
    }

    public TaxeSejourTrimestrielleService getTaxeSejourTrimestrielleService() {
        return taxeSejourTrimestrielleService;
    }

    public void setTaxeSejourTrimestrielleService(TaxeSejourTrimestrielleService taxeSejourTrimestrielleService) {
        this.taxeSejourTrimestrielleService = taxeSejourTrimestrielleService;
    }

    public AbstractConverter<TaxeSejourTrimestrielle, TaxeSejourTrimestrielleVo> getTaAbstractConverter() {
        return taAbstractConverter;
    }

    public void setTaAbstractConverter(AbstractConverter<TaxeSejourTrimestrielle, TaxeSejourTrimestrielleVo> taAbstractConverter) {
        this.taAbstractConverter = taAbstractConverter;
    }

    
}
