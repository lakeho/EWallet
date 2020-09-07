package vn.itsol.MSWallet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MSWallet.dto.UserWalletDisplay;
import vn.itsol.MSWallet.dto.WalletDto;
import vn.itsol.MSWallet.service.wallet.WalletService;

import java.util.List;

@RestController
@RequestMapping(path = "/wallet")
@CrossOrigin(origins = "http://localhost:4200")
public class WalletController
{
    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;
    @GetMapping(path = "findbyuserid/{user_id}")
    public List<UserWalletDisplay> getByUserId(@PathVariable("user_id") int user_id)
    {
        return walletService.findWalletbyuserid(user_id);
    }

    @GetMapping(path = "findbywalletid/{wallet_id}")
    public List<UserWalletDisplay> getByWalletId(@PathVariable("wallet_id") int wallet_id)
    {
        return walletService.findWalletbywalletid(wallet_id);
    }

    @GetMapping(path = "findbyid/{wallet_id}")
    public WalletDto getWallet(@PathVariable("wallet_id") int wallet_id)
    {
        return walletService.getWallet(wallet_id);
    }

    @PostMapping(path = "save")
    public void saveWallet(@RequestBody WalletDto walletDto)
    {
        walletService.save(walletDto);
    }

    @PostMapping(path = "save2")
    public WalletDto saveWallet2(@RequestBody WalletDto walletDto)
    {
        return walletService.save2(walletDto);
    }

    @PutMapping(path = "update")
    public void updateWallet(@RequestBody WalletDto walletDto)
    {
        walletService.update(walletDto);
    }

    @DeleteMapping(path = "delete/{wallet_id}")
    public void deleteWallet(@PathVariable("wallet_id") int wallet_id)
    {
        walletService.delete(wallet_id);
    }
}
