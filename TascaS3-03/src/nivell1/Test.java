package nivell1;

public class Test<T> extends Producte{

    private T obj;

    public Test(String nom, String tipus, double preu, Object obj) {
        super(nom, tipus, preu);
        this.obj = (T) obj;
    }

    public T getObject() { return this.obj; }

    @Override
    public String toString() {
        return tipus + ";" + nom + ";" + obj + ";" + preu;
    }
}
