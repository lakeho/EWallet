package vn.itsol.MSWallet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MSWallet.dto.UsersDto;
import vn.itsol.MSWallet.service.users.UsersService;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController
{
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @GetMapping(path = "findbyid/{user_id}")
    public UsersDto getUser(@PathVariable("user_id") int user_id)
    {
        log.info("UsersController: " + usersService.GetUser(user_id));
        return usersService.GetUser(user_id);
    }

    @GetMapping(path = "findbyusername")
    public UsersDto getUserName(@RequestParam(name = "username") String username)
    {
        log.info("UsersController: " + usersService.findByUserName(username));
        return usersService.findByUserName(username);
    }

    @GetMapping(path = "findbyphone")
    public UsersDto getByPhone(@RequestParam(name = "phone") int phone)
    {
        log.info("UsersController: " + usersService.findByPhone(phone));
        return usersService.findByPhone(phone);
    }

    @GetMapping(path = "findbyname")
    public List<UsersDto> getByName(@RequestParam(name = "name") String name)
    {
        log.info("UsersController: " + usersService.findByname(name));
        return usersService.findByname(name);
    }

    @GetMapping(path = "findbysex")
    public List<UsersDto> getBySex(@RequestParam(name = "name") String gender)
    {
        log.info("UsersController: " + usersService.findBySex(gender));
        return usersService.findBySex(gender);
    }

    @GetMapping(path = "findbyusernamepass")
    public UsersDto getUserNamePass(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password)
    {
        log.info("UsersController: " + usersService.findByUserNamePass(username, password));
        return usersService.findByUserNamePass(username, password);
    }

    @PostMapping(path = "save")
    public void saveUser(@RequestBody UsersDto usersDto){
        usersService.save(usersDto);

    }

    @PutMapping(path = "update")
    public void updateUser(@RequestBody UsersDto usersDto){
        usersService.update(usersDto);
    }
}
