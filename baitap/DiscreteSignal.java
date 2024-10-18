package ktra;

public class DiscreteSignal {
    private int n0;  // Chỉ số bắt đầu
    private int[] h;

    // Constructor khởi tạo n0 và mảng h
    public DiscreteSignal(int n0, int[] h) {
        this.n0 = n0;
        this.h = h;
    }

    // Phương thức tính giá trị tín hiệu tại chỉ số n
    public double computeSignal(int n) {
        int delta = deltaFunction(n - n0); // Kiểm tra điều kiện delta

        if (n - n0 >= 0 && n - n0 < h.length) {
            return delta * h[n - n0];
        } else {
            return 0;
        }
    }

    // Phương thức in giá trị tín hiệu tại các chỉ số được chỉ định
    public void printSignal(int[] indices) {
        System.out.println("Tín hiệu x(n) tại các chỉ số:");
        for (int n : indices) {
            double signalValue = computeSignal(n);
            System.out.println("x(" + n + ") = " + signalValue);
        }
    }

    private int deltaFunction(int n) {
        return (n == 0) ? 1 : 0;
    }

    public static void main(String[] args) {
        int n0 = 2;
        int[] h = {1, 2, 3, 4, 5};

        DiscreteSignal signal = new DiscreteSignal(n0, h);

        int[] indices = {0, 1, 2, 3, 4}; 
        signal.printSignal(indices);
    }
}