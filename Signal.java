package ktra;

public interface Signal {         //tạo giao diện Signal
    void genterateSinal()
}
public class DiscreteSignal implements Signal {  //tạo class DiscreteSignal kế thừa từ interface Signal
    void genterateSignal();
    System.out.println("Day la DiscreteSignal");
}

public class ContinuousSignal implements Signal {  //Tạo class ContinuoussSignal kế thừa từ interface Signal
    void generateSignal();
        System.out.println("Day la ContinuoussSignal");
}

    
public class Main {
    public static void main(String[] args) {
        DiscreteSignal discreteSignal = new DiscreteSignal();
        ContinuousSignal continuousSignal = new ContinuousSignal();
    
        discreteSignal.generateSignal();
        continuousSignal.generateSignal();
    }
    
}