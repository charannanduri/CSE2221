import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Charan
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bot = model.bottom();

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bot);
        view.updateSubtractAllowed(bot.compareTo(top) <= 0);
        view.updateDivideAllowed(bot.isZero() == false);
        view.updateRootAllowed(
                bot.compareTo(INT_LIMIT) <= 0 && bot.compareTo(TWO) >= 0);
        view.updatePowerAllowed(bot.compareTo(TWO) >= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        top.copyFrom(bot);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();

        top.add(bot);
        bot.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        top.subtract(bot);
        bot.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        top.multiply(bot);
        bot.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        NaturalNumber r = top.divide(bot);

        bot.transferFrom(top);
        top.transferFrom(r);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        int n = bot.toInt();
        top.power(n);
        bot.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();
        int n = bot.toInt();
        top.root(n);
        bot.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bot = this.model.bottom();

        bot.multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

}
