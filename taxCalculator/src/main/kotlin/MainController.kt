import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class MainController: ActionListener, KeyListener {
    /* Variables */
    private val model: CalculatorModel = CalculatorModel()
    private val view: Display = Display()

    /* Constructor */
    constructor() {
        view.setActionListener(this)
        view.setKeyListener(this)
    }

    /* Methods */
    private fun updateDisplay(monthlyIncome: Double) {
        model.computeTaxBreakdown(monthlyIncome)
        view.updateDisplay(model.sSSContribution, model.pagIbigContribution, model.philHealthContribution, model.totalContributions, model.taxableIncome, model.incomeTax, model.totalDeductions, model.netMonthlyPay)
    }

    private fun updateDisplay() {
        view.updateDisplay()
    }

    override fun actionPerformed(e: ActionEvent?) {
        val text = view.textField.text
        val value: Double

        try {
            value = text.toDouble()
            this.updateDisplay(value)
        } catch(e: NumberFormatException) {
            this.updateDisplay()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        var code = e.keyCode
        val text = view.textField.text
        val value: Double

        if(code == KeyEvent.VK_ENTER) {
            try {
                value = text.replace(",", "").toDouble()
                this.updateDisplay(value)
            } catch(e: NumberFormatException) {
                this.updateDisplay()
            }
        } else if(e.keyChar in '0'..'9' || e.keyChar == '.' || e.keyChar == ',' || e.keyCode == KeyEvent.VK_BACK_SPACE || e.keyCode == KeyEvent.VK_DELETE || e.keyCode == KeyEvent.VK_LEFT || e.keyCode == KeyEvent.VK_RIGHT) {
            view.textField.isEditable = true
        } else {
            view.textField.isEditable = false
        }
    }

    override fun keyTyped(e: KeyEvent?) {
        //DO NOTHING
    }

    override fun keyReleased(e: KeyEvent?) {
        //DO NOTHING
    }
}