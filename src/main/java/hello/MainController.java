package hello;

import dataBaseConnect.Commands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class MainController {
    private Commands commands = new Commands();

    @Value("This is made by Thymeleaf")
    private String message;

    @Value("First Name & Last Name is required!")
    private String errorMessage;

    @Value("Incorrect email address!")
    private String errorMessageEmail;

    @Value("The maximum number of characters is 50")
    private String errorMessageNumber;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", commands.list());
        return "personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        String email = personForm.getEmail();
        String date = getDateTime();

        try {
            if (firstName.length() > 50 || lastName.length() > 50|| email.length() > 50) {
                model.addAttribute("errorMessage", errorMessageNumber);
                return "addPerson";

            }else if (firstName.length() > 0 && lastName.length() > 0) {
                commands.add(firstName, lastName, email, date);
                return "redirect:/personList";
            }
            model.addAttribute("errorMessage", errorMessage);
            return "addPerson";
        }catch (Exception e){
            model.addAttribute("errorMessage", errorMessageEmail);
            return "addPerson";
        }
    }

    @RequestMapping(value = {"/clearData"}, method = RequestMethod.GET)
    public String clearData(){
        return "clearData";
    }

    @RequestMapping(value = {"/confirmClear"}, method = RequestMethod.GET)
    public String confirmClear(){
        commands.clear();
        return "redirect:/personList";
    }

    @RequestMapping(value = {"/abort"})
    public String abort(){
        return "redirect:/personList";
    }

    @RequestMapping(value = {"/logOut"}, method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/action"}, method = RequestMethod.POST)
    public String action(@RequestParam(name = "action") String action){
        if (action.equals("value1")){return "redirect:/page1";}
        else if (action.equals("value2")){return "redirect:/page2";}
        return action;
    }

    @RequestMapping(value = {"/page1"})
    public String page1(){
        return "/page1";
    }

    @RequestMapping(value = {"/page2"})
    public String page2(){
        return "/page2";
    }

    @GetMapping("/date")
    public String date (Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "date";
    }

    @RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
    public String userInfo(Model model) {
        int id = 1;
        model.addAttribute("persons", commands.user(id));
        return "userInfo";
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }
}
