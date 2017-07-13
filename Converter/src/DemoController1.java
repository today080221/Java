/**
 * Controller class.
 *
 * @author Chen
 */
public final class DemoController1 implements DemoController {

    /**
     * Model object.
     */
    private final DemoModel model;

    /**
     * View object.
     */
    private final DemoView view;

    /**
     * Setup private immutable data
     */
    final char[] Oalphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z' };
    final String[] Talphabet = { "𝓪", "𝓫", "𝓬", "𝓭", "𝓮", "𝓯", "𝓰", "𝓱",
            "𝓲", "𝓳", "𝓴", "𝓵", "𝓶", "𝓷", "𝓸", "𝓹", "𝓺", "𝓻", "𝓼",
            "𝓽", "𝓾", "𝓿", "𝔀", "𝔁", "𝔂", "𝔃", "𝓐", "𝓑", "𝓒", "𝓓",
            "𝓔", "𝓕", "𝓖", "𝓗", "𝓘", "𝓙", "𝓚", "𝓛", "𝓜", "𝓝", "𝓞",
            "𝓟", "𝓠", "𝓡", "𝓢", "𝓣", "𝓤", "𝓥", "𝓦", "𝓧", "𝓨", "𝓩" };
    final String[] Lalphabet = { "𝖆", "𝖇", "𝖈", "𝖉", "𝖊", "𝖋", "𝖌", "𝖍",
            "𝖎", "𝖏", "𝖐", "𝖑", "𝖒", "𝖓", "𝖔", "𝖕", "𝖖", "𝖗", "𝖘",
            "𝖙", "𝖚", "𝖛", "𝖜", "𝖝", "𝖞", "𝖟", "𝕬", "𝕭", "𝕮", "𝕯",
            "𝕰", "𝕱", "𝕲", "𝕳", "𝕴", "𝕵", "𝕶", "𝕷", "𝕸", "𝕹", "𝕺",
            "𝕻", "𝕼", "𝕽", "𝕾", "𝕿", "𝖀", "𝖁", "𝖂", "𝖃", "𝖄", "𝖅" };

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(DemoModel model, DemoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        String output = model.output();
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public DemoController1(DemoModel model, DemoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.setOutput("");
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes copy event.
     *
     * @param input
     *            value of input text (provided by view)
     */
    @Override
    public void processConvertEvent(String input, int index) {
        /*
         * Update model in response to this event
         */
        String ans = "";
        if (index == 1) {
            ans = this.result(input, this.Lalphabet);
        } else {
            ans = this.result(input, this.Talphabet);
        }
        this.model.setOutput(ans);
        this.model.setInput(input);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Find the corresponding character's index in the array, return -1 if not
     * found.
     *
     * @param alphabet
     *            the Original alphabet
     * @param ch
     *            the target character
     * @return the index of that character
     */
    private int checkIn(char[] alphabet, char ch) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Convert the input text into target font.
     *
     * @param input
     *            the input text
     * @param alphabet
     *            the target alphabet
     * @return the input converted to the target font
     */
    private String result(String input, String[] alphabet) {
        String ans = "";
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int check = this.checkIn(this.Oalphabet, ch);
            if (check != -1) {
                ans = ans + alphabet[check];
            } else {
                ans = ans + ch;
            }
        }
        return ans;
    }

}
