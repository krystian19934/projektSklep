package sklep;

import java.util.*;

public class SiećSklepów {
    Map<String,Map<GatunekProduktu, List<Produkt>>> mapa = new HashMap<>();

    public SiećSklepów() {
    }

    public SiećSklepów(Map<String, Map<GatunekProduktu, List<Produkt>>> mapa) {
        this.mapa = mapa;
    }

    public Map<String, Map<GatunekProduktu, List<Produkt>>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, Map<GatunekProduktu, List<Produkt>>> mapa) {
        this.mapa = mapa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiećSklepów that = (SiećSklepów) o;

        return mapa != null ? mapa.equals(that.mapa) : that.mapa == null;
    }

    @Override
    public int hashCode() {
        return mapa != null ? mapa.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SiećSklepów{" +
                "mapa=" + mapa +
                '}';
    }

    public void dodajSklepDoSieci(Sklep sklep){
        mapa.put(sklep.getMiasto(),sklep.getMapaSklepu());
    }

    public Set<String> znajdzSklepyWKtorychJestDanyProdukt(Produkt produkt){
        Set <String> sklepyWKtorychJestDanyProdukt = new HashSet<>();
        for(Map.Entry<String,Map<GatunekProduktu,List<Produkt>>> wpis : mapa.entrySet()){
            for (Map.Entry<GatunekProduktu,List<Produkt>> mapaSklepuWpis : wpis.getValue().entrySet()){
                for(Produkt p : mapaSklepuWpis.getValue()){
                    if(p.equals(produkt)){
                        sklepyWKtorychJestDanyProdukt.add(wpis.getKey());
                        break;
                    }
                }
            }
        }
        return sklepyWKtorychJestDanyProdukt;
    }

    public Set<Producent> znajdzProducentowDostepnychWDanymMiescie(String miasto){
        Set<Producent> producenciWDanymMiescie = new HashSet<>();
        if(mapa.containsKey(miasto)){
            for(Map.Entry<String,Map<GatunekProduktu,List<Produkt>>> wpis : mapa.entrySet()){
                if(wpis.getKey().equals(miasto)){
                    for (Map.Entry<GatunekProduktu,List<Produkt>> mapaSklepuWpis : wpis.getValue().entrySet()){
                        for(Produkt p : mapaSklepuWpis.getValue()){
                            producenciWDanymMiescie.add(p.getProducent());
                        }
                    }

                }
            }
        }
        return producenciWDanymMiescie;
    }

    public List<Produkt> znajdzProduktyKonkretnegoGatunkuIKonkretnegoProducentaWDanymMiescie(GatunekProduktu gatunekProduktu,Producent producent,String miasto){
        List<Produkt> produkty = new ArrayList<>();
        if(mapa.containsKey(miasto)){
            for (Map.Entry<String,Map<GatunekProduktu, List<Produkt>>> wpis : mapa.entrySet()) {
                if(wpis.getKey().equalsIgnoreCase(miasto)){
                    for (Map.Entry<GatunekProduktu,List<Produkt>> mapaSklepuWpis : wpis.getValue().entrySet()){
                        if(mapaSklepuWpis.getKey().equals(gatunekProduktu)){
                            for(Produkt p : mapaSklepuWpis.getValue()){
                                if(p.getProducent().equals(producent)){
                                    produkty.add(p);
                                }
                            }

                        }
                    }
                }
            }
        }
        return produkty;
    }

    public static void main(String[] args) {

        Sklep sklep1 = new Sklep("Kraków",new HashMap<>());
        Sklep sklep2 = new Sklep("Częstochowa", new HashMap<>());
        Sklep sklep3 = new Sklep("Wrocław", new HashMap<>());

        Produkt p1 = new Produkt(GatunekProduktu.CERAMIKA, "filizanka", Producent.PROCERAMIK, 8);
        Produkt p2 = new Produkt(GatunekProduktu.CERAMIKA, "kubek", Producent.PROCERAMIK, 2);
        Produkt p3 = new Produkt(GatunekProduktu.TEKSTYLIA, "zaslona", Producent.FIRANEX, 5);
        Produkt p4 = new Produkt(GatunekProduktu.METALOWE, "wiertlo do metalu", Producent.MAKITA, 10);
        Produkt p5 = new Produkt(GatunekProduktu.METALOWE, "wiertlo do betonu", Producent.BOSCH, 18);
        Produkt p6 = new Produkt(GatunekProduktu.NARZĘDZIA, "mlotek", Producent.FISCHER, 8);
        Produkt p7 = new Produkt(GatunekProduktu.TEKSTYLIA, "firanka", Producent.ZASŁONEX, 15);
        Produkt p8 = new Produkt(GatunekProduktu.NARZĘDZIA, "klucz plasko-oczkowy", Producent.WKRĘTMET, 25);

        sklep1.dodajProduktDoSklepu(p1);
        sklep1.dodajProduktDoSklepu(p1);
        sklep1.dodajProduktDoSklepu(p1);
        sklep1.dodajProduktDoSklepu(p1);
        sklep1.dodajProduktDoSklepu(p3);
        sklep1.dodajProduktDoSklepu(p6);

        sklep2.dodajProduktDoSklepu(p2);
        sklep2.dodajProduktDoSklepu(p4);

        sklep3.dodajProduktDoSklepu(p5);
        sklep3.dodajProduktDoSklepu(p7);
        sklep3.dodajProduktDoSklepu(p8);

        System.out.println(sklep1);
        System.out.println();
        System.out.println(sklep2);
        System.out.println();
        System.out.println(sklep3);
        System.out.println();

        SiećSklepów siećSklepów = new SiećSklepów();

        siećSklepów.dodajSklepDoSieci(sklep1);
        siećSklepów.dodajSklepDoSieci(sklep2);
        siećSklepów.dodajSklepDoSieci(sklep3);

        System.out.println(siećSklepów.znajdzProduktyKonkretnegoGatunkuIKonkretnegoProducentaWDanymMiescie(GatunekProduktu.CERAMIKA,Producent.PROCERAMIK,"Częstochowa"));

        System.out.println(siećSklepów.znajdzProducentowDostepnychWDanymMiescie("Częstochowa"));

        System.out.println(siećSklepów.znajdzSklepyWKtorychJestDanyProdukt(p1));




    }
}
