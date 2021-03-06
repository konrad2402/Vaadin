package com.mycompany.aaa;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.client.metadata.TypeDataStore;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.security.pkcs11.Secmod;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.mycompany.aaa.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    final VerticalLayout layout = new VerticalLayout();
    public List<Osoba> db = new ArrayList<Osoba>();
    Label lab =new Label();
    final TextField searchName = new TextField("Szukaj");
    private Table table = new Table("Czlowieki ;)");
    
    public void addperson(){
            Osoba o = new Osoba();
            Osoba x = new Osoba();
            Osoba y = new Osoba();
            o.setMiasto("Kolonia");
            o.setImie("Konrad");
            o.setNazwisko("Nowal");
            o.setTelefon("668822709");
            o.setOpis("Lubi motocykle i wycieczki, student.");
            db.add(o);
            x.setMiasto("Szczytno");
            x.setImie("Mietek");
            x.setNazwisko("Kowalski");
            x.setTelefon("251486223");
            x.setOpis("Dobry programista PHP, lubi filmy.");
            db.add(x);
            y.setMiasto("Olsztyn");
            y.setImie("test");
            y.setNazwisko("test");
            y.setTelefon("1111");
            y.setOpis("Mało interesujący, właściwie kto to jest?.");
            db.add(y);
    }
    
    public List<Osoba> getAllosoba()
    {
            return db;
    }
    public void szczegol(Osoba z ,VerticalLayout layout1 ){
        String tel= z.getTelefon();
        String szczeg="";
        for ( Osoba p : db){
                    if(tel.equals(p.getTelefon())){
                       szczeg =p.getImie()+": "+p.getOpis();
                    }
        }
         layout1.removeComponent(lab);
         lab.setValue(szczeg);
         layout1.addComponent(lab);
    }
    public void pokaOsobe(final VerticalLayout layout1){
        {
           table.removeAllItems();
            layout1.removeComponent(table);
		table.addContainerProperty("Imie", String.class, null);
                table.addContainerProperty("Nazwisko", String.class, null);
		table.addContainerProperty("Miasto", String.class, null);
                table.addContainerProperty("Telefon", String.class, null);
		table.addContainerProperty("Szczegóły", Button.class, null);
                table.addContainerProperty("Usuń", Button.class, null);
		for (final Osoba p : db){
			Item item = table.addItem(p.getImie());
			item.getItemProperty("Imie").setValue(p.getImie());
                        item.getItemProperty("Nazwisko").setValue(p.getNazwisko());
			item.getItemProperty("Miasto").setValue(p.getMiasto());
                        item.getItemProperty("Telefon").setValue(p.getTelefon());
                       
                final Button show = new Button("Szczegóły", new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        szczegol(p , layout1);
                     }
            });  
                final Button del = new Button("Usun", new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        db.remove(p);
                        pokaOsobe(layout1);
                     }
            });  
                item.getItemProperty("Szczegóły").setValue(show);
                item.getItemProperty("Usuń").setValue(del);
            }
            }
		layout1.addComponent(table);
	}
        
     public void autorzyacja(MyVaadinUI ui ,VerticalLayout layout2 ,String pass , String login)
    {
        for ( Osoba p : db){
                    if(login.equals(p.getImie())&&pass.equals(p.getTelefon())){
                        ui.getSession().setAttribute("myValue", "1");
                        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("status", "1");
                    }
        
        }
        if("0".equals(VaadinService.getCurrentRequest().getWrappedSession()
                        .getAttribute("status")))
        {
            layout2.addComponent(new Label("Nie Udane logowanie"));
        }
        getPage().setLocation(getPage().getLocation());
        
    }
    public void logout()
    {
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("status", "0");
        getPage().setLocation(getPage().getLocation());
    }
       
    @Override
    protected void init(VaadinRequest request) {
        addperson();
        final VerticalLayout layout2 = new VerticalLayout();
        final VerticalLayout layout3 = new VerticalLayout();
        layout.setMargin(true);
         layout.setSpacing(true);
          layout2.setSpacing(true);
           layout3.setSpacing(true);
        setContent(layout);
        layout.addComponent(layout2);
        layout.addComponent(layout3);
        layout2.setWidth("40%");
        layout3.setWidth("40%");
        layout2.setMargin(true);
        layout3.setMargin(false);
        
        if("1".equals(VaadinService.getCurrentRequest().getWrappedSession()
                        .getAttribute("status")))
        {
           final Button logoutt = new Button("Wyloguj", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                logout();
                }
            });
            layout3.addComponent(logoutt); 
            
            pokaOsobe(layout2);
        }
        else
        {
           final TextField login = new TextField("Imie");
           final PasswordField password = new PasswordField("Numer telefonu");
           layout2.addComponent(login); 
            layout2.addComponent(password); 
            final Button loginButton = new Button("Logowanko", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                    autorzyacja(MyVaadinUI.this,layout,  password.getValue(),login.getValue() );
                }
            });
            layout2.addComponent(loginButton); 
            Property.ValueChangeListener checkForm = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(login.isValid() && password.isValid()) {
                    loginButton.setEnabled(true);
                } else {
                    loginButton.setEnabled(false);
                }
            }
        };
        //walidacja imienia
        login.addValidator(new StringLengthValidator("Login minimum 2 znaków", 2, Integer.MIN_VALUE, false));
        // walidacja hasla
        password.addValidator(new StringLengthValidator("Hasło minimum 4 znaków", 4, Integer.MIN_VALUE, false));
        login.addValueChangeListener(checkForm);
        password.addValueChangeListener(checkForm);
        login.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        password.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        login.setImmediate(true);
        password.setImmediate(true);
        }
    }
}