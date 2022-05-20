package com.vsk.demo.controller;

import com.vsk.demo.beans.FirstBean;
import com.vsk.demo.beans.TestBean;
//import com.vsk.demo.db.DBManager;
//import com.vsk.demo.db.Items;
import com.vsk.demo.entities.ShopItems;
import com.vsk.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Adil") String name,
                           @RequestParam(name="surname", required=false, defaultValue="Ozkoch") String surname, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "greeting";
    }

    @Autowired
    private ItemService itemService;


    @GetMapping("/")
    public String index(Model model){

        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("tovary", items);

//        String text = firstBean.getText()+" "+secondBean.getText();
//        model.addAttribute("text", text);

        return "index";
    }

//    @RequestMapping()

    @PostMapping("/additem")
    public String addItem(@RequestParam(name="name", defaultValue="No Item" ) String name,
                          @RequestParam(name="price", defaultValue="0" ) int price,
                          @RequestParam(name="amount", defaultValue="0" ) int amount) {

//        Items item = new Items(null, name, price, amount);

itemService.addItem(new ShopItems(null, name, price, amount));

//        DBManager.addI(item);
        return "redirect:/";

    }

    @GetMapping(value="/details")
    public String details(Model model, @RequestParam(name="id") Long id){

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "details";
    }

    @PostMapping("/saveitem")
    public String saveItem(@RequestParam(name = "id" ,defaultValue = "0") Long id,
                           @RequestParam(name="name", defaultValue="No Item" ) String name,
                           @RequestParam(name="price", defaultValue="0" ) int price,
                           @RequestParam(name="amount", defaultValue="0" ) int amount){

        ShopItems item = itemService.getItem(id);
        if(item!=null){
            item.setName(name);
            item.setAmount(amount);
            item.setPrice(price);
            itemService.saveItem(item);
        }

        return "redirect:/";

    }

    @PostMapping("/deleteitem")
    public String deleteItem(@RequestParam(name="id") Long id){

        ShopItems item = itemService.getItem(id);
        if(item!=null){
            itemService.deleteItem(item);
        }
        return "redirect:/";
    }

@GetMapping("/success")
    public String success(){
        return "success";
}

    @GetMapping("/additem")
    public String addItemForm(){
        return "additem";
    }

}
