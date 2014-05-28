package com.mycompany.test1;

import com.google.gwt.resources.client.CssResource;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.annotations.Push;

@Push 
@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.mycompany.test1.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final TextField name = new TextField("Imię:");
        final TextField nazwisko = new TextField("Nazwisko:");
        final TextField email = new TextField("Mail:");
        final PasswordField haslo = new PasswordField("Hasło:");
        final TextField kod = new TextField("Kod Pocztowy:");
        final TextField miasto = new TextField("Miasto:");
        
        final Button send = new Button("Wyślij");
        
        Property.ValueChangeListener checkForm = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(name.isValid() && email.isValid() && haslo.isValid() && kod.isValid()) {
                    send.setEnabled(true);
                } else {
                    send.setEnabled(false);
                }
            }
        };
        name.addValueChangeListener(checkForm);
        nazwisko.addValueChangeListener(checkForm);
        email.addValueChangeListener(checkForm);
        haslo.addValueChangeListener(checkForm);
        kod.addValueChangeListener(checkForm);
        miasto.addValueChangeListener(checkForm);
        name.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        nazwisko.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        email.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        haslo.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        kod.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        miasto.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);

        // imię
        name.addValidator(new RegexpValidator("^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłóńśźż ]+$", "Zły format"));
        name.addValidator(new StringLengthValidator("Za krutkie!", 3, 100, true));
        
        // nazwisko
        nazwisko.addValidator(new RegexpValidator("^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłóńśźż ]+$", "Niepoprawny format"));
        nazwisko.addValidator(new StringLengthValidator("Za krutkie!", 3, 100, true));
        
        // e-mail
        email.addValidator(new EmailValidator("Zły e-mail"));
        email.addValidator(new StringLengthValidator("Musisz tu coś wpisać :/", 1, Integer.MIN_VALUE, false));

        // hasło
        haslo.addValidator(new StringLengthValidator("Minimum 10 znaków!", 10, Integer.MIN_VALUE, false));

        // kod
        kod.addValidator(new RegexpValidator("^[0-9][0-9]-[0-9][0-9][0-9]$", "Niepoprawny format"));
        kod.addValidator(new StringLengthValidator("Wpisz coś!", 1, Integer.MIN_VALUE, false));
        
        // miasto
        miasto.addValidator(new RegexpValidator("^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłóńśźż ]+$", "Niepoprawny format"));
        miasto.addValidator(new StringLengthValidator("Za krutkie!", 3, 100, true));
        
        // wyświetl
        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.addStyleName("konrad");
        layout.setSpacing(true);
        setContent(layout);

        name.setImmediate(true);
        nazwisko.setImmediate(true);
        email.setImmediate(true);
        haslo.setImmediate(true);
        kod.setImmediate(true);
        miasto.setImmediate(true);
        
        layout.addComponent(name);
        layout.addComponent(nazwisko);
        layout.addComponent(email);
        layout.addComponent(haslo);
        layout.addComponent(kod);
        layout.addComponent(miasto);

        

        send.setEnabled(false);
        send.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(name.getValue()));
                layout.addComponent(new Label(nazwisko.getValue()));
                layout.addComponent(new Label(email.getValue()));
                layout.addComponent(new Label(kod.getValue()+"    "+miasto.getValue()));
                
            }
        });
        layout.addComponent(send);
       
    }

}    
