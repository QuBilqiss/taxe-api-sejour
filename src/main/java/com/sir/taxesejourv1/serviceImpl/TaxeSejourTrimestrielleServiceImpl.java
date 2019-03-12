/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.taxesejourv1.serviceImpl;

import com.sir.taxesejourV1.util.DateUtil;
import com.sir.taxesejourv1.bean.TauxTaxeSejour;
import com.sir.taxesejourv1.bean.TaxeSejourTrimestrielle;
import com.sir.taxesejourv1.dao.TaxeSejourTrimestrielleDao;
import com.sir.taxesejourv1.rest.exchage.vo.CategorieVo;
import com.sir.taxesejourv1.rest.exchage.vo.LocalVo;
import com.sir.taxesejourv1.rest.proxy.CategorieProxy;
import com.sir.taxesejourv1.rest.proxy.LocalProxy;
import com.sir.taxesejourv1.service.TauxTaxeSejourService;
import com.sir.taxesejourv1.service.TaxeSejourTrimestrielleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class TaxeSejourTrimestrielleServiceImpl implements TaxeSejourTrimestrielleService {

    @Autowired
    private TaxeSejourTrimestrielleDao taxeSejourTrimestrielleDao;
    @Autowired
    private TauxTaxeSejourService tauxTaxeSejourService;
    @Autowired
    private LocalProxy localProxy;
    @Autowired
    private CategorieProxy categorieProxy;

    @Override  // nous peremetons de creer le taxe 
    public int creertaxe(TaxeSejourTrimestrielle taxe) {
       TaxeSejourTrimestrielle t = findByReference(taxe.getReference());
       int res=0;
        if (t != null) {
            return -1;
        } 
        else {
            // chercher le pourcentage du tauxtrimestriel
            LocalVo localvo = localProxy.findByReference(taxe.getReferenceLocal());
            CategorieVo cat = localvo.getCategorieVo();
            System.out.println(" categorie est : "+cat);
            TauxTaxeSejour tauxTaxeSejour=tauxTaxeSejourService.findByRefCategorie(cat.getRefCategorie());
            System.out.println(" le taux est "+tauxTaxeSejour);
            double pourcentage=tauxTaxeSejour.getPourcentage();
          
       // permet de calculer le nomber de mois de retard          
      int nombreMoisRetard = (int) DateUtil.calculDiff(taxe.getDatePresentation(),DateUtil.calculerDateByTrimAndAnnee(taxe.getNumeroTrimester(), taxe.getAnnee()));
            System.out.println("le nombre de mois de retard est:"+nombreMoisRetard);          
    // calculer les monatants
            double montant = 0;
            double nvMontantDeBase = taxe.getChiffreAffaire() * pourcentage;
            double nvMontantMajoration = nvMontantDeBase * taxe.getMajoration();
            double nvMontantPenalite = nvMontantDeBase * taxe.getPenalite();
            // vérifie le nomber de mois de retard et calculer le montant taxe
            if (nombreMoisRetard == 0) {
                montant += nvMontantDeBase;
                res=1;
            } else if (nombreMoisRetard == 1) {
                montant += nvMontantDeBase + nvMontantMajoration;
                taxe.setMontantMajoration(nvMontantMajoration);
                res= 2;
            } else if (nombreMoisRetard > 1) {
                montant += nvMontantDeBase + nvMontantMajoration + nvMontantPenalite*nombreMoisRetard;
                taxe.setMontantMajoration(nvMontantMajoration);
                taxe.setMontantPenalite(nvMontantPenalite);
                res= 3;
            }
            taxe.setMontantTaxe(montant);
            taxe.setNomberMoisRetard(nombreMoisRetard);
            taxe.setMontantBase(nvMontantDeBase);
            taxeSejourTrimestrielleDao.save(taxe);
            return nombreMoisRetard;
        }
    }
    
    @Override  // permet de trouver un taxe d'apres leur reference 
    public TaxeSejourTrimestrielle findByReference(String reference) {
        return taxeSejourTrimestrielleDao.findByReference(reference);
    }

    @Override // permet de trouver tous les taxe assicie a un local d'apres reference de local
    public List<TaxeSejourTrimestrielle> findByReferenceLocal(String referenceLocal){
        return taxeSejourTrimestrielleDao.findByReferenceLocal(referenceLocal);
    }
     @Override
    public List<TaxeSejourTrimestrielle> findByCriteria(Integer annee, Double montantMin, Double montantMax) {
        return taxeSejourTrimestrielleDao.findByCriteria(annee, montantMin, montantMax);
    }

    @Override
    public List<TaxeSejourTrimestrielle> findAll() {
        return taxeSejourTrimestrielleDao.findAll();
    }
    
    public CategorieProxy getCategorieProxy() {
        return categorieProxy;
    }

    public void setCategorieProxy(CategorieProxy categorieProxy) {
        this.categorieProxy = categorieProxy;
    }
    
    
    
    public TaxeSejourTrimestrielleDao getTaxeSejourTrimestrielleDao() {
        return taxeSejourTrimestrielleDao;
    }

    public void setTaxeSejourTrimestrielleDao(TaxeSejourTrimestrielleDao taxeSejourTrimestrielleDao) {
        this.taxeSejourTrimestrielleDao = taxeSejourTrimestrielleDao;
    }

    public LocalProxy getLocalProxy() {
        return localProxy;
    }

    public void setLocalProxy(LocalProxy localProxy) {
        this.localProxy = localProxy;
    }

    public TauxTaxeSejourService getTauxTaxeSejourService() {
        return tauxTaxeSejourService;
    }

    public void setTauxTaxeSejourService(TauxTaxeSejourService tauxTaxeSejourService) {
        this.tauxTaxeSejourService = tauxTaxeSejourService;
    }

    

   

    

}
