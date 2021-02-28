public class Main {

    public static void main(String[] args) {
        Invs ccc = new Invs();
        ccc.load(new Testclass());
        System.out.println(ccc.getCountOfStaticMethods());
    }
}
