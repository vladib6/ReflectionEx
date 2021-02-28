public class Testclass implements Interface{
   public Testclass(){}

   int a ,b;
   String string1, string2;
   final int d=2;

    @Override
    public void print() {
        System.out.println("Interface");
    }
    public static int get(){
        return 1000;
    }
}
