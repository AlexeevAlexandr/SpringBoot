package hello;

import dataBaseConnect.Commands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        try {
            if (firstName.length() > 50 || lastName.length() > 50|| email.length() > 50) {
                model.addAttribute("errorMessage", errorMessageNumber);
                return "addPerson";

            }else if (firstName.length() > 0 && lastName.length() > 0) {
                commands.add(firstName, lastName, email);
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
}
