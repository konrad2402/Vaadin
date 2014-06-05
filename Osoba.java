/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.aaa;

/**
 *
 * @author Konrad
 */
public class Osoba {
    private static final long serialVersionUID = 1L;
	private String Imie="Konrad";
	private String Nazwisko="Nowak";
	private String Telefon="332244324" ;
        private String Miasto="Kolonia";
        private String Opis="Jest fajny!";


public Osoba(){}

	
	public Osoba(String Imie, String Nazwisko, String Telefon, String Miasto, String Opis){
		this.Imie = Imie;
		this.Nazwisko = Nazwisko;
		this.Miasto = Miasto;
                this.Telefon = Telefon;
                this.Opis = Opis;
	}
        
        public String getImie() {
		return Imie;
	}
	public void setImie(String Imie) {
		this.Imie = Imie;
	}
         public String getOpis() {
		return Opis;
	}
	public void setOpis(String Opis) {
		this.Opis = Opis;
	}
        
        public String getNazwisko() {
		return Nazwisko;
	}
	public void setNazwisko(String Nazwisko) {
		this.Nazwisko = Nazwisko;
	}
        
        
        public String getMiasto() {
		return Miasto;
	}
	public void setMiasto(String Miasto) {
		this.Miasto = Miasto;
	}
        
        
        public String getTelefon() {
		return Telefon;
	}
	public void setTelefon(String Telefon) {
		this.Telefon = Telefon;
	}
        
         
}
