import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SwingCalculator {
    private enum Operation {
        DIVISION,
        MULTIPLICATION,
        SUBTRACTION,
        ADDITION,
        DEFAULT
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
                new JButton("0"), new JButton(".")
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

        // Variables to store the nums
        AtomicReference<Double> ans = new AtomicReference<>(0.0);
        AtomicReference<Double> lastNum = new AtomicReference<>();

        // Row 1
        nums[0].setLocation(20,160); // 7
        nums[0].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"7");
            numTyped.set(true);
        });

        nums[1].setLocation(190,160); // 8
        nums[1].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"8");
            numTyped.set(true);
        });

        nums[2].setLocation(360,160); // 9
        nums[2].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"9");
            numTyped.set(true);
        });

        // Row 2
        nums[3].setLocation(20,260); // 4
        nums[3].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"4");
            numTyped.set(true);
        });

        nums[4].setLocation(190,260); // 5
        nums[4].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"5");
            numTyped.set(true);
        });

        nums[5].setLocation(360,260); // 6
        nums[5].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"6");
            numTyped.set(true);
        });

        // Row 3
        nums[6].setLocation(20,360); // 1
        nums[6].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"1");
            numTyped.set(true);
        });

        nums[7].setLocation(190,360); // 2
        nums[7].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"2");
            numTyped.set(true);
        });

        nums[8].setLocation(360,360); // 3
        nums[8].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"3");
            numTyped.set(true);
        });

        // Row 4
        nums[9].setBounds(20,460,320,80); // 0
        nums[9].addActionListener(b -> {
            if (ansShowing.get()) {
                tf.setText("");
                ansShowing.set(false);
            }
            tf.setText(tf.getText()+"0");
            numTyped.set(true);
        });

        nums[10].setBounds(360,460,150,80); // .
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

        // Operations Buttons
        ops[0].setLocation(530,160); // Divide
        ops[0].addActionListener(b -> {
            lastNum.set(Double.parseDouble(tf.getText()));
            currentOperation.set(Operation.DIVISION);
            if (ansShowing.get())
                tf.setText("");
            if (numTyped.get()) {
                tf.setText("");
                numTyped.set(false);
                decimalPressed.set(false);
            }
        });

        ops[1].setLocation(530,240); // Multiply
        ops[1].addActionListener(b -> {
            lastNum.set(Double.parseDouble(tf.getText()));
            currentOperation.set(Operation.MULTIPLICATION);
            if (ansShowing.get())
                tf.setText("");
            if (numTyped.get()) {
                tf.setText("");
                numTyped.set(false);
                decimalPressed.set(false);
            }
        });

        ops[2].setLocation(530,320); // Subtract
        ops[2].addActionListener(b -> {
            lastNum.set(Double.parseDouble(tf.getText()));
            currentOperation.set(Operation.SUBTRACTION);
            if (ansShowing.get())
                tf.setText("");
            if (numTyped.get()) {
                tf.setText("");
                numTyped.set(false);
                decimalPressed.set(false);
            }
        });

        ops[3].setLocation(530,400); // Add
        ops[3].addActionListener(b -> {
            lastNum.set(Double.parseDouble(tf.getText()));
            currentOperation.set(Operation.ADDITION);
            if (ansShowing.get())
                tf.setText("");
            if (numTyped.get()) {
                tf.setText("");
                numTyped.set(false);
                decimalPressed.set(false);
            }
        });

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
