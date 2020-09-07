package vn.itsol.MSWallet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MSWallet.dto.TransactionsDisplay;
import vn.itsol.MSWallet.dto.TransactionsDto;
import vn.itsol.MSWallet.service.transactions.TransactionsService;

import java.util.List;

@RestController
@RequestMapping(path = "/trans")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionsController
{
    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping(path = "display/{id}")
    public List<TransactionsDisplay> getTransactionDisplay(@PathVariable("id") int id){
        List<TransactionsDisplay> results = transactionsService.getTransactionsDisplay(id);
        return results;
    }

    @GetMapping(path = "findbycategoryid/{id}")
    public List<TransactionsDto> getTransactionByCategory(@PathVariable("id") int id){
        List<TransactionsDto> results = transactionsService.getTransactionsCategory(id);
        return results;
    }

    @GetMapping(path = "findbywalletid/{id}")
    public List<TransactionsDto> getTransactionByWallet(@PathVariable("id") int id){
        List<TransactionsDto> results = transactionsService.getTransactionsWallet(id);
        return results;
    }

    @GetMapping(path = "findbyid/{id}")
    public TransactionsDto getTransaction(@PathVariable("id") int id){
        TransactionsDto result = transactionsService.gettran(id);
        return result;
    }

    @PostMapping(path = "save")
    public void saveTransaction(@RequestBody TransactionsDto transactionsDto) {
        transactionsService.save(transactionsDto);
    }

    @PutMapping(path = "update")
    public void updateTransaction(@RequestBody TransactionsDto transactionsDto) {
        transactionsService.update(transactionsDto);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteTransaction(@PathVariable("id") long id){
        try {
            transactionsService.delete(id);
        }
        catch (Exception e){
            log.error(e.toString());
        }
    }

    @DeleteMapping(path = "delByWId/{id}")
    public void deleteByWalletId(@PathVariable("id") long id){
        try{
            transactionsService.deleteByWalletId(id);
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
