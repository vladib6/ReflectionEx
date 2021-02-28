public class Main {
    class AAA{
        private  int b,a;

        public AAA() {
            System.out.println("AAA Ctor");
        }
    }

    public static void main(String[] args) {
        Invs ccc = new Invs();
        ccc.load(new Invs());
        System.out.println(ccc.getAllImplementedInterfaces());
    }
}
