package sklep;

import java.util.*;

public class Sklep {

    private Comparator<Produkt> BY_CENA = new Comparator<Produkt>() {
        @Override
        public int compare(Produkt o1, Produkt o2) {
            return o1.getCena().compareTo(o2.getCena());
        }
    };

    private String miasto;
    private Queue<Produkt> kolejkaProduktow = new PriorityQueue<>(BY_CENA);
    private Map<GatunekProduktu,List<Produkt>> mapaSklepu = new HashMap<>();

    public Sklep() {
    }

    public Sklep(String miasto,Map<GatunekProduktu, List<Produkt>> mapaSklepu){
        this.miasto = miasto;
        this.mapaSklepu = mapaSklepu;
    }

    public Sklep(String miasto, Queue<Produkt> kolejkaProduktow, Map<GatunekProduktu, List<Produkt>> mapaSklepu) {
        this.miasto = miasto;
        this.kolejkaProduktow = kolejkaProduktow;
        this.mapaSklepu = mapaSklepu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Queue<Produkt> getKolejkaProduktow() {
        return kolejkaProduktow;
    }

    public void setKolejkaProduktow(Queue<Produkt> kolejkaProduktow) {
        this.kolejkaProduktow = kolejkaProduktow;
    }

    public Map<GatunekProduktu, List<Produkt>> getMapaSklepu() {
        return mapaSklepu;
    }

    public void setMapaSklepu(Map<GatunekProduktu, List<Produkt>> mapaSklepu) {
        this.mapaSklepu = mapaSklepu;
    }

    @Override
    public String toString() {
        return "Sklep{" +
                "miasto='" + miasto + '\'' +
                ", kolejkaProduktow=" + kolejkaProduktow +
                ", mapaSklepu=" + mapaSklepu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sklep sklep = (Sklep) o;

        if (BY_CENA != null ? !BY_CENA.equals(sklep.BY_CENA) : sklep.BY_CENA != null) return false;
        if (miasto != null ? !miasto.equals(sklep.miasto) : sklep.miasto != null) return false;
        if (kolejkaProduktow != null ? !kolejkaProduktow.equals(sklep.kolejkaProduktow) : sklep.kolejkaProduktow != null)
            return false;
        return mapaSklepu != null ? mapaSklepu.equals(sklep.mapaSklepu) : sklep.mapaSklepu == null;
    }

    @Override
    public int hashCode() {
        int result = BY_CENA != null ? BY_CENA.hashCode() : 0;
        result = 31 * result + (miasto != null ? miasto.hashCode() : 0);
        result = 31 * result + (kolejkaProduktow != null ? kolejkaProduktow.hashCode() : 0);
        result = 31 * result + (mapaSklepu != null ? mapaSklepu.hashCode() : 0);
        return result;
    }

    public List<Produkt> zwrocWszystkieDostepneArtykulyDanegoGatunkuDoPodanejCeny(GatunekProduktu gatunek, Integer cena){
        List<Produkt> listaDoDanejCeny = new ArrayList<>();
        for(Produkt p : kolejkaProduktow){
            if(p.getGatunekProduktu().equals(gatunek) || p.getCena() < cena){
                listaDoDanejCeny.add(p);
            }
        }

        return listaDoDanejCeny;
    }

    public List<Produkt> zwrocWszystkieDostepneProduktyDanegoGatunku(GatunekProduktu gatunek){
        List<Produkt> listaPoGatunku = new ArrayList<>();
        for(Produkt p : kolejkaProduktow){
            if(p.getGatunekProduktu().equals(gatunek)){
                listaPoGatunku.add(p);
            }
        }
        return listaPoGatunku;
    }

    public List<Produkt> zwrocWszystkieDostepneProduktyDanegoProducenta(Producent producent){
        List<Produkt> listaPoProducencie = new ArrayList<>();
        for(Produkt p : kolejkaProduktow){
            if(p.getProducent().equals(producent)){
                listaPoProducencie.add(p);
            }
        }
        return listaPoProducencie;
    }

    public void usunProduktZeSklepu(Produkt produkt){
        if(mapaSklepu.containsKey(produkt.getGatunekProduktu())){
            mapaSklepu.get(produkt.getGatunekProduktu()).remove(produkt);
        }
        kolejkaProduktow.remove(produkt);
    }

    public void dodajProduktDoSklepu(Produkt produkt){
        kolejkaProduktow.offer(produkt);
        if(!mapaSklepu.containsKey(produkt.getGatunekProduktu())){
            if(mapaSklepu.get(produkt.getGatunekProduktu())== null){
                List<Produkt> listaProduktow = new ArrayList<>();
                listaProduktow.add(produkt);
                mapaSklepu.put(produkt.getGatunekProduktu(),listaProduktow);
            }
            else{
                mapaSklepu.get(produkt.getGatunekProduktu()).add(produkt);
            }
        }
    }


}
