
import Abi.AbiKlass;
import Abi.KaartUus;


import Abi.Kasutaja;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import java.util.ArrayList;
import java.util.Collections;



public class Main {
    public Main(Kasutaja kasutaja) throws LWJGLException {
        AbiKlass.alusta(AbiKlass.getEkraanX(), AbiKlass.getEkraanY());
        KaartUus kaart = new KaartUus(kasutaja);
        while(AbiKlass.isMängKäib()) {
            if(Display.isCloseRequested()){
                AbiKlass.setMängKäib(false);
            }
            AbiKlass.klaviatuuriKäsitlus();
            if (AbiKlass.getMenüüOlek() == 0 || AbiKlass.getMenüüOlek() == 2 || AbiKlass.getMenüüOlek() == 3) {
                hiireKäsitlus(kasutaja);
                if(!AbiKlass.isMängKäib()){
                    break;
                }
                kaart.joonista(AbiKlass.getEkraanX(), AbiKlass.getEkraanY());

            } else if (AbiKlass.getMenüüOlek() == 1) {
                kaart.muudaNulliks();
                for (int i = 0; i < kaart.getSuurusx() - 1; i++) {
                    kaart.liigutaKõikAlla();
                }

                kaart.joonista(AbiKlass.getEkraanX(), AbiKlass.getEkraanY());
                kaart.uuedKlotsid();
                if(!AbiKlass.isMänguPaus()) {
                    while (Mouse.next()) {
                        if (Mouse.getEventButtonState()) {
                            if (Mouse.getEventButton() == 0) { //kui vajutatakse alla hiire vasak nupp, leiab algse koordinaadi
                                AbiKlass.setxKoord((int) Math.floor(Mouse.getX() / (AbiKlass.getEkraanX() / kaart.getSuurusx()))); // leiab kursori asukoha maatriksi suhtes
                                AbiKlass.setyKoord((int) (kaart.getSuurusy() - 1 - Math.floor(Mouse.getY() / (AbiKlass.getEkraanY() / kaart.getSuurusy()))));
                                AbiKlass.setTest(true);
                            }
                        } else { // kui getEventButtonState on false, ehk hiir pole (enam) alla vajutatud
                            if (Mouse.getEventButton() == 0) {
                                kaart.setKaartElement(AbiKlass.getxKoord(), AbiKlass.getyKoord(), AbiKlass.hiireLoogika(AbiKlass.getDeltaX(), AbiKlass.getDeltaY(), AbiKlass.getEkraanX(), AbiKlass.getEkraanY()));
                                AbiKlass.setTest(false);
                            }
                        }
                        if (AbiKlass.isTest()) { //kui hiire vasak nupp on vajutatud, siis salvestab kursori x ja y muutumist

                            AbiKlass.addDeltaX(Mouse.getDX());
                            AbiKlass.addDeltaY(Mouse.getDY());
                        } else if (!AbiKlass.isTest()) { // kui vasak nupp lahti lastakse, siis nullib x ja y koordinaadi muutumise
                            AbiKlass.setDeltaX(0);
                            AbiKlass.setDeltaY(0);
                        }

                    }
                }



            }
            Display.update();
            Display.sync(60);
        }
        kirjutaKasutajad(kaart, kasutaja);
        Display.destroy();


    }
    /*public static void main(String[] args) throws Exception{
        new Main(new Kasutaja("asd", "asd", 1));
    }*/



    public static void hiireKäsitlus(Kasutaja kasutaja) throws LWJGLException { //Kuna abiklass on eraldi, siis antud viisil ei saaks hästi resolutsiooni muuta
        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    if(AbiKlass.getMenüüOlek() == 0) {//esimenüü
                        if (Mouse.getX() > (0.273 * AbiKlass.getEkraanX()) && Mouse.getX() < (0.74 * AbiKlass.getEkraanX())
                                && Mouse.getY() < (AbiKlass.getEkraanY() - (0.43 * AbiKlass.getEkraanY())) && Mouse.getY() > (AbiKlass.getEkraanY() - (0.49 * AbiKlass.getEkraanY()))) {
                            AbiKlass.setMenüüOlek(1);
                        } else if (Mouse.getX() > (0.35 * AbiKlass.getEkraanX()) && Mouse.getX() < (0.664 * AbiKlass.getEkraanX())
                                && Mouse.getY() < (AbiKlass.getEkraanY() - (0.56 * AbiKlass.getEkraanY())) && Mouse.getY() > (AbiKlass.getEkraanY() - (0.625 * AbiKlass.getEkraanY()))) {
                            AbiKlass.setMenüüOlek(2);
                        } else if (Mouse.getX() > (0.45 * AbiKlass.getEkraanX()) && Mouse.getX() < (0.585 * AbiKlass.getEkraanX())
                                && Mouse.getY() < (AbiKlass.getEkraanY() - (0.7 * AbiKlass.getEkraanY())) && Mouse.getY() > (AbiKlass.getEkraanY() - (0.76 * AbiKlass.getEkraanY()))) {
                            AbiKlass.setMenüüOlek(3);
                        }
                    }
                    else if(AbiKlass.getMenüüOlek() == 2) {//sätted menüü
                        if (Mouse.getX() > (0.37 * AbiKlass.getEkraanX()) && Mouse.getX() < (0.65 * AbiKlass.getEkraanX())
                                && Mouse.getY() < (AbiKlass.getEkraanY() - (0.5 * AbiKlass.getEkraanY())) && Mouse.getY() > (AbiKlass.getEkraanY() - (0.56 * AbiKlass.getEkraanY()))) {
                                Display.destroy();


                                if(AbiKlass.getEkraanX() == 1000) {

                                        AbiKlass.setEkraanX(800);
                                        AbiKlass.setEkraanY(600);
                                        new Main(kasutaja);
                                        AbiKlass.setMängKäib(false);
                                        return;

                                }else{

                                        AbiKlass.setEkraanX(1000);
                                        AbiKlass.setEkraanY(800);
                                        new Main(kasutaja);
                                        AbiKlass.setMängKäib(false);
                                        return;

                                }
                        }
                    }
                }
            }
        }

    }

    public static void kirjutaKasutajad(KaartUus kaart, Kasutaja kasutaja){
        ArrayList<Kasutaja> kasutajad = KirjutaFaili.loefailist();
        Kasutaja kasutaja2 = kaart.getKasutaja();
        if(kasutaja2.getSkoor() < kaart.getVanaskoor()){
            kasutaja2.setSkoor(kaart.getVanaskoor());
        }
        for(int i = 0; i<kasutajad.size(); i++){
            if(kasutajad.get(i).getAcc().equals(kasutaja.getAcc())){
                kasutajad.remove(i);
                kasutajad.add(kasutaja2);
            }
        }
        Collections.sort(kasutajad);
        KirjutaFaili.kirjuta(kasutajad);
    }

}