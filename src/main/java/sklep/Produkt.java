package sklep;

public class Produkt {
    private GatunekProduktu gatunekProduktu;
    private String nazwaProduktu;
    private Producent producent;
    private  Integer cena;

    public Produkt() {
    }

    public Produkt(GatunekProduktu gatunekProduktu, String nazwaProduktu, Producent producent, Integer cena) {
        this.gatunekProduktu = gatunekProduktu;
        this.nazwaProduktu = nazwaProduktu;
        this.producent = producent;
        this.cena = cena;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public GatunekProduktu getGatunekProduktu() {
        return gatunekProduktu;
    }

    public void setGatunekProduktu(GatunekProduktu gatunekProduktu) {
        this.gatunekProduktu = gatunekProduktu;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "gatunekProduktu=" + gatunekProduktu +
                ", nazwaProduktu='" + nazwaProduktu + '\'' +
                ", producent=" + producent +
                ", cena=" + cena +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produkt produkt = (Produkt) o;

        if (gatunekProduktu != produkt.gatunekProduktu) return false;
        if (nazwaProduktu != null ? !nazwaProduktu.equals(produkt.nazwaProduktu) : produkt.nazwaProduktu != null)
            return false;
        if (producent != produkt.producent) return false;
        return cena != null ? cena.equals(produkt.cena) : produkt.cena == null;
    }

    @Override
    public int hashCode() {
        int result = gatunekProduktu != null ? gatunekProduktu.hashCode() : 0;
        result = 31 * result + (nazwaProduktu != null ? nazwaProduktu.hashCode() : 0);
        result = 31 * result + (producent != null ? producent.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }
}
