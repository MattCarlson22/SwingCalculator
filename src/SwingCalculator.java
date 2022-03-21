import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SwingCalculator {
    enum Operation {
        DIVISION,
        MULTIPLICATION,
        SUBTRACTION,
        ADDITION,
        DEFAULT
    }
    static void numsActionListener(JButton button, JFormattedTextField textField, AtomicBoolean ans, AtomicBoolean num, String text) {
        button.addActionListener(b -> {
            if (ans.get()) {
                textField.setText("");
                ans.set(false);
            }
            textField.setText(textField.getText()+text);
            num.set(true);
        });
    }
    static void opsActionListener(JButton button, JFormattedTextField textField, AtomicBoolean ans, AtomicBoolean num, AtomicBoolean decimal, AtomicBoolean neg, AtomicReference<Operation> op1, AtomicReference<Double> last, Operation op2) {
        button.addActionListener(b -> {
            last.set(Double.parseDouble(textField.getText()));
            op1.set(op2);
            if (ans.get())
                textField.setText("");
            if (num.get()) {
                textField.setText("");
                num.set(false);
                decimal.set(false);
                neg.set(false);
            }
        });
    }
    public static void main(String[] args) {

        AtomicReference<Operation> currentOperation = new AtomicReference<>();
        currentOperation.set(Operation.DEFAULT);
        final JFrame f = new JFrame();
        final JFormattedTextField tf = new JFormattedTextField();

        JButton[] nums = {
                new JButton("7"), new JButton("8"), new JButton("9"),
                new JButton("4"), new JButton("5"), new JButton("6"),
                new JButton("1"), new JButton("2"), new JButton("3"),
                new JButton("0"), new JButton("."), new JButton("C"),
                new JButton("(-)")
        };

        JButton[] ops = {
                new JButton("/"),
                new JButton("*"),
                new JButton("-"),
                new JButton("+"),
                new JButton("=")
        };

        Font font = tf.getFont();
        float fontSize = font.getSize() + 20.0f;

        // Creating the window
        f.setSize(800,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the Text Field
        tf.setBounds(20,20,740,120);
        tf.setBackground(Color.LIGHT_GRAY);
        tf.setFocusable(false);

        // Creating Number Buttons
        for (JButton num : nums) {
            num.setFont(font.deriveFont(fontSize));
            num.setSize(150,80);
            num.setBackground(Color.WHITE);
        }

        // Creating Operation Buttons
        for (JButton op : ops) {
            op.setFont(font.deriveFont(fontSize));
            op.setSize(230,60);
            op.setBackground(Color.WHITE);
        }

        // bools
        AtomicBoolean numTyped = new AtomicBoolean(false);
        AtomicBoolean ansShowing = new AtomicBoolean(false);
        AtomicBoolean decimalPressed = new AtomicBoolean(false);
        AtomicBoolean negPressed = new AtomicBoolean(false);

        // Variables to store the nums
        AtomicReference<Double> ans = new AtomicReference<>(0.0);
        AtomicReference<Double> lastNum = new AtomicReference<>();

        // Row 1
        nums[0].setLocation(20,160); // 7
        SwingCalculator.numsActionListener(nums[0],tf,ansShowing,numTyped,"7");

        nums[1].setLocation(190,160); // 8
        SwingCalculator.numsActionListener(nums[1],tf,ansShowing,numTyped,"8");

        nums[2].setLocation(360,160); // 9
        SwingCalculator.numsActionListener(nums[2],tf,ansShowing,numTyped,"9");

        // Row 2
        nums[3].setLocation(20,260); // 4
        SwingCalculator.numsActionListener(nums[3],tf,ansShowing,numTyped,"4");

        nums[4].setLocation(190,260); // 5
        SwingCalculator.numsActionListener(nums[4],tf,ansShowing,numTyped,"5");

        nums[5].setLocation(360,260); // 6
        SwingCalculator.numsActionListener(nums[5],tf,ansShowing,numTyped,"6");

        // Row 3
        nums[6].setLocation(20,360); // 1
        SwingCalculator.numsActionListener(nums[6],tf,ansShowing,numTyped,"1");

        nums[7].setLocation(190,360); // 2
        SwingCalculator.numsActionListener(nums[7],tf,ansShowing,numTyped,"2");

        nums[8].setLocation(360,360); // 3
        SwingCalculator.numsActionListener(nums[8],tf,ansShowing,numTyped,"3");

        // Row 4
        nums[11].setLocation(20,460); // Clear
        nums[11].addActionListener(b -> {
            tf.setText("");
            lastNum.set(0.0);
            numTyped.set(false);
            decimalPressed.set(false);
            ansShowing.set(false);
        });

        nums[9].setLocation(190,460); // 0
        SwingCalculator.numsActionListener(nums[9],tf,ansShowing,numTyped,"0");

        nums[10].setBounds(360,460,150,35); // .
        nums[10].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            if (!decimalPressed.get()) {
                tf.setText(tf.getText() + ".");
                decimalPressed.set(true);
            }
            numTyped.set(true);
        });

        nums[12].setBounds(360,505,150,35); // (-)
        nums[12].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            if (!negPressed.get() && !numTyped.get()) {
                tf.setText("-");
                negPressed.set(true);
            }
        });

        // Operations Buttons
        ops[0].setLocation(530,160); // Divide
        SwingCalculator.opsActionListener(ops[0],tf,ansShowing,numTyped,decimalPressed,negPressed,currentOperation,lastNum,Operation.DIVISION);

        ops[1].setLocation(530,240); // Multiply
        SwingCalculator.opsActionListener(ops[1],tf,ansShowing,numTyped,decimalPressed,negPressed,currentOperation,lastNum,Operation.MULTIPLICATION);

        ops[2].setLocation(530,320); // Subtract
        SwingCalculator.opsActionListener(ops[2],tf,ansShowing,numTyped,decimalPressed,negPressed,currentOperation,lastNum,Operation.SUBTRACTION);

        ops[3].setLocation(530,400); // Add
        SwingCalculator.opsActionListener(ops[3],tf,ansShowing,numTyped,decimalPressed,negPressed,currentOperation,lastNum,Operation.ADDITION);

        ops[4].setLocation(530,480); // Equals
        ops[4].addActionListener(b -> {
            switch (currentOperation.get()) {
                case DEFAULT -> ans.set(Double.parseDouble(tf.getText()));
                case DIVISION -> ans.set((lastNum.get()) / (Double.parseDouble(tf.getText())));
                case MULTIPLICATION -> ans.set((lastNum.get()) * (Double.parseDouble(tf.getText())));
                case ADDITION -> ans.set((lastNum.get()) + (Double.parseDouble(tf.getText())));
                case SUBTRACTION -> ans.set((lastNum.get()) - (Double.parseDouble(tf.getText())));
            }
            tf.setText(Double.toString(ans.get()));
            ansShowing.set(true);
            decimalPressed.set(false);
            numTyped.set(false);
            negPressed.set(false);
        });

        // Making the Font Bigger
        tf.setFont(font.deriveFont(fontSize));

        // Adding Buttons and Frame
        f.add(tf);
        for (JButton num : nums)
            f.add(num);
        for (JButton op : ops)
            f.add(op);
    }
}
