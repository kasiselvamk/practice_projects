public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Unique Test.");
         new Thread(() -> {
          while(true)
               System.out.println("T1:"+SequenceNumber.EXP.Next());

        }).start();

        new Thread(() -> {
           while(true)
               System.out.println("T2:"+SequenceNumber.EXP.Next());

        }).start();

        new Thread(() -> {
             while(true)
            System.out.println("T3:"+SequenceNumber.EXP.Next());

        }).start();

        new Thread(() -> {
             while(true)
            System.out.println("T4:"+SequenceNumber.EXP.Next());

        }).start();
    }
}
