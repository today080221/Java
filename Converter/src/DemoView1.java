import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * View class.
 *
 * @author Chen
 */
@SuppressWarnings("serial")
public final class DemoView1 extends JFrame implements DemoView {

    /**
     * Controller object.
     */
    private DemoController controller;

    /**
     * GUI widgets that need to be in scope in actionPerformed method, and
     * related constants. (Each should have its own Javadoc comment, but these
     * are elided here to keep the code shorter.)
     */
    private static final int LINES_IN_TEXT_AREAS = 5,
            LINE_LENGTHS_IN_TEXT_AREAS = 20, ROWS_IN_BUTTON_PANEL_GRID = 1,
            COLUMNS_IN_BUTTON_PANEL_GRID = 2, ROWS_IN_THIS_GRID = 1,
            COLUMNS_IN_THIS_GRID = 1;

    /**
     * Text areas.
     */
    private final JTextArea inputText, outputText;

    /**
     * Buttons.
     */
    private final JButton convertButton, gothic;

    /**
     * Labels.
     */
    private final JLabel input, output;

    /**
     * No-argument constructor.
     */
    public DemoView1() {
        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Text Convertor by Chen");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Create widgets
         */
        this.inputText = new JTextArea("", LINES_IN_TEXT_AREAS,
                LINE_LENGTHS_IN_TEXT_AREAS);
        this.outputText = new JTextArea("", LINES_IN_TEXT_AREAS,
                LINE_LENGTHS_IN_TEXT_AREAS);
        this.convertButton = new JButton("转圆体");
        this.gothic = new JButton("转哥特体");
        this.input = new JLabel("原版文字");
        this.output = new JLabel("转换文字");
        /*
         * Text areas should wrap lines, and outputText should be read-only
         */
        this.inputText.setEditable(true);
        this.inputText.setLineWrap(true);
        this.inputText.setWrapStyleWord(true);
        this.outputText.setEditable(false);
        this.outputText.setLineWrap(true);
        this.outputText.setWrapStyleWord(true);
        /*
         * Create scroll panes for the text areas in case text is long enough to
         * require scrolling in one or both dimensions
         */
        JScrollPane inputTextScrollPane = new JScrollPane(this.inputText);
        JScrollPane outputTextScrollPane = new JScrollPane(this.outputText);
        /*
         * Create a button panel organized using grid layout
         */
        JPanel buttonPanel = new JPanel(new FlowLayout(1, 1, 1));
        /*
         * Create input and output panel
         */
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPanel outputPanel = new JPanel(new GridLayout(2, 1));
        /*
         * Create label panels
         */
        JPanel inLabelPanel = new JPanel(new FlowLayout());
        JPanel outLabelPanel = new JPanel(new FlowLayout());
        /*
         * Create mainPanel
         */
        JPanel mainPanel = new JPanel(new FlowLayout());
        /*
         * Add the buttons to the button panel, from left to right and top to
         * bottom
         */
        buttonPanel.add(this.convertButton);
        buttonPanel.add(this.gothic);
        /*
         * Add the labels in panels
         */
        inLabelPanel.add(this.input);
        outLabelPanel.add(this.output);
        /*
         * Add the text in the panels
         */
        inputPanel.add(inLabelPanel);
        inputPanel.add(inputTextScrollPane);
        outputPanel.add(outLabelPanel);
        outputPanel.add(outputTextScrollPane);
        /*
         * Add Panels to the main panel
         */
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(outputPanel);
        /*
         * Organize main window using grid layout
         */
        this.setLayout(new GridLayout(ROWS_IN_THIS_GRID, COLUMNS_IN_THIS_GRID));
        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */
        this.add(mainPanel);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */
        this.convertButton.addActionListener(this);
        this.gothic.addActionListener(this);
        // Start the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized for the widgets in
         * it, that it exits this program when closed, and that it becomes
         * visible to the user now
         */
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     *
     * @param controller
     *            controller to register
     */
    @Override
    public void registerObserver(DemoController controller) {
        this.controller = controller;
    }

    /**
     * Updates input display based on String provided as argument.
     *
     * @param input
     *            new value of input display
     */
    @Override
    public void updateInputDisplay(String input) {
        this.inputText.setText(input);
    }

    /**
     * Updates output display based on String provided as argument.
     *
     * @param output
     *            new value of output display
     */
    @Override
    public void updateOutputDisplay(String output) {
        this.outputText.setText(output);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.gothic) {
            this.controller.processConvertEvent(this.inputText.getText(), 1);
        } else if (source == this.convertButton) {
            this.controller.processConvertEvent(this.inputText.getText(), 0);
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
