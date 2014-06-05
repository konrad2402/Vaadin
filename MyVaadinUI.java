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
            o.setTelefon("111111");
            db.add(o);
            x.setMiasto("Szczytno");
            x.setImie("Mietek");
            x.setNazwisko("Kowalski");
            x.setTelefon("33333");
            db.add(x);
            y.setMiasto("Olsztyn");
            y.setImie("test");
            y.setNazwisko("test");
            y.setTelefon("1111");
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
                       szczeg ="Nazywam się " + p.getImie()+" "+p.getNazwisko()+",mieszkam w "+p.getMiasto()+". Mój nr tel"+p.getTelefon()+" Zadzwoń !!!";
                    }
        
        }
        
         layout1.removeComponent(lab);
         lab.setValue(szczeg);
         layout1.addComponent(lab);
        
        
    }
    public void dajosobe(int indeks ,VerticalLayout layout2 )
    {
        for ( Osoba p : db){
                    if("elooo".equals(p.getImie())){
			
                        //layout2.addComponent();
                    }
        
        }
    }
    
    
     public void autorzyacja(MyVaadinUI ui ,VerticalLayout layout2 ,String pass , String login)
    {
        for ( Osoba p : db){
                    if(login.equals(p.getImie())&&pass.equals(p.getTelefon())){
                        ui.getSession().setAttribute("myValue", "1");
                        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("status", "1");
//layout2.addComponent(new Label("Udane logowanie"));
                        
                    }
        
        }
        getPage().setLocation(getPage().getLocation());
        
        if("0".equals(VaadinService.getCurrentRequest().getWrappedSession()
                        .getAttribute("status")))
        {
            layout2.addComponent(new Label("Nie Udane logowanie"));
        }
    }
       
    @Override
    protected void init(VaadinRequest request) {
        addperson();
        
        final TextField imie = new TextField("Podaj imie aby się zalogować:");
        final VerticalLayout layout2 = new VerticalLayout();
        final HorizontalLayout layout3 = new HorizontalLayout();
      dajosobe(1,layout2);
        layout.setMargin(true);
         layout.setSpacing(true);
          layout2.setSpacing(true);
           layout3.setSpacing(true);
        setContent(layout);
        layout.addComponent(layout2);
        layout.addComponent(layout3);
        layout2.setWidth("50%");
        layout3.setWidth("50%");
        layout2.setMargin(true);
        layout3.setMargin(false);
        //private String[] talica = {"marek", "konrad"};
        
           final TextField login = new TextField("Imie");
           final PasswordField password = new PasswordField("Numer telefonu");
           layout2.addComponent(login); 
            login.focus();
            layout2.addComponent(password); 
            final Button loginButton = new Button("Logowanko", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                 // saveValue(SettingReadingSessionAttributesUI.this, value);
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


//JSONObject obj = new JSONObject();
	//obj.put("name", "mkyong.com");
       // JSONParser parser = new JSONParser();
 
/*	Button button = new Button ("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                for(int l=0 ;l<db.size();l++)
                {
                layout2.addComponent(new Label(db.get(l).getImie()));
                layout3.addComponent(new Label(db.get(l).getTelefon()));
                
                }
            }
        });
layout2.addComponent(button);
try {
 
		//bject obj = parser.parse(new FileReader("C:\\Users\\Konrad\\Documents\\GitHub\\wizyt-wka-Vaadin\\wizytowkakody.json");
 
		//JSONObject jsonObject = (JSONObject) obj;
 
		//String name = (String) jsonObject.get("name");
		//System.out.println(name);
 
		long age = (Long) jsonObject.get("age");
		System.out.println(age);
 
		// loop array
		JSONArray msg = (JSONArray) jsonObject.get("messages");
		Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
  */     
//http://e-java.pl/kurs-java/czesc-2-pierwsze-kroki/wyswietlanie-informacji-na-ekranie/
//http://www.mkyong.com/java/json-simple-example-read-and-write-json/
//http://javastart.pl/klasy/arrays-operacje-na-tablicach/