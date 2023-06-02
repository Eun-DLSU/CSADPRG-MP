import java.awt.*
import java.awt.event.ActionListener
import java.awt.event.KeyListener
import java.math.BigDecimal
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.Border
import javax.swing.border.EmptyBorder

class Display: JFrame {
    /* Variables */
    private val screenWidth = 728
    private val screenHeight = 728
    private val backgroundColor = Color(247, 250, 252)
    private val borderColor = Color(203, 213, 224)
    private val emptyBorder = EmptyBorder(20, 20, 20, 20)
    private val lineBorder = BorderFactory.createLineBorder(borderColor, 3)
    private val fullBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder)
    private val fontSize = 17

    //For Input Panel
    private val inputPanel = JPanel()
    private val textInputPanel = JPanel()
    private val buttonPanel = JPanel()
    val textField = JTextField(30)
    private val textLabel = JLabel("Enter Monthly Income")
    private val submitButton = JButton("Calculate")

    //Center Panel
    private val centerPanel = JPanel()

    //For Monthly Contributions
    private val monthlyContributionPanel = JPanel()
    private val sssPanel = JPanel()
    private val pagibigPanel = JPanel()
    private val philhealthPanel = JPanel()
    private val contributionPanel = JPanel()
    private val sssLabel = JLabel()
    private val pagibigLabel = JLabel()
    private val philhealthLabel = JLabel()
    private val totalContributionsLabel = JLabel()
    private val sssValue = JLabel()
    private val pagibigValue = JLabel()
    private val philhealthValue = JLabel()
    private val totalContributionsValue = JLabel()

    //For Income Tax
    private val incomeTaxCalculationPanel = JPanel()
    private val taxableIncomeLabel = JLabel()
    private val taxableIncomeValue = JLabel()
    private val incomeTaxLabel = JLabel()
    private val incomeTaxValue = JLabel()
    private val taxableIncomePanel = JPanel()
    private val incomeTaxPanel = JPanel()

    //For Breakdown
    private val breakdownPanel = JPanel()
    private val totalDeductionsPanel = JPanel()
    private val netPayPanel = JPanel()
    private val totalDeductionsLabel = JLabel()
    private val totalDeductionsValue = JLabel()
    private val netPayLabel = JLabel()
    private val netPayValue = JLabel()

    /* Constructor */
    constructor() {
        initializeDisplay()
    }

    /* Methods */
    private fun initializeDisplay() {
        this.title = "Tax Calculator"
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.isResizable = false
        this.layout = BorderLayout()
        this.isFocusable = true
        this.size = Dimension(screenWidth, screenHeight)
        this.background = backgroundColor

        initializeInputPanel()
        initializeCenterPanel()
        initializeFinalBreakdownPanel()

        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    private fun initializeInputPanel() {
        inputPanel.layout = BorderLayout()
        inputPanel.border = fullBorder

        textInputPanel.layout = FlowLayout()
        textLabel.font = Font("Serif", Font.PLAIN, fontSize)
        textField.font = Font("Serif", Font.PLAIN, fontSize)

        textInputPanel.add(textLabel)
        textInputPanel.add(textField)

        buttonPanel.layout = FlowLayout()
        buttonPanel.add(submitButton)

        inputPanel.add(textInputPanel, BorderLayout.CENTER)
        inputPanel.add(buttonPanel, BorderLayout.SOUTH)

        this.add(inputPanel, BorderLayout.NORTH)
    }

    private fun initializeCenterPanel() {
        centerPanel.layout = GridLayout(1, 2)
        centerPanel.border = fullBorder
        initializeMonthlyContributionsPanel()
        initializeMonthlyIncomeTaxPanel()
        this.add(centerPanel, BorderLayout.CENTER)
    }

    private fun initializeMonthlyContributionsPanel() {
        monthlyContributionPanel.layout = GridLayout(4, 1)
        monthlyContributionPanel.border = fullBorder

        sssLabel.text = "SSS"
        sssLabel.font = Font("Serif", Font.PLAIN, fontSize)
        sssLabel.border = emptyBorder
        sssValue.text = ""
        sssValue.font = Font("Serif", Font.PLAIN, fontSize)
        sssValue.border = emptyBorder
        sssPanel.layout = BorderLayout()
        sssPanel.add(sssLabel, BorderLayout.WEST)
        sssPanel.add(sssValue, BorderLayout.EAST)

        pagibigLabel.text = "PagIbig"
        pagibigLabel.font = Font("Serif", Font.PLAIN, fontSize)
        pagibigLabel.border = emptyBorder
        pagibigValue.text = ""
        pagibigValue.font = Font("Serif", Font.PLAIN, fontSize)
        pagibigValue.border = emptyBorder
        pagibigPanel.layout = BorderLayout()
        pagibigPanel.add(pagibigLabel, BorderLayout.WEST)
        pagibigPanel.add(pagibigValue, BorderLayout.EAST)

        philhealthLabel.text = "PhilHealth"
        philhealthLabel.font = Font("Serif", Font.PLAIN, fontSize)
        philhealthLabel.border = emptyBorder
        philhealthValue.text = ""
        philhealthValue.font = Font("Serif", Font.PLAIN, fontSize)
        philhealthValue.border = emptyBorder
        philhealthPanel.layout = BorderLayout()
        philhealthPanel.add(philhealthLabel, BorderLayout.WEST)
        philhealthPanel.add(philhealthValue, BorderLayout.EAST)

        totalContributionsLabel.text = "Contributions"
        totalContributionsLabel.font = Font("Serif", Font.PLAIN, fontSize)
        totalContributionsLabel.border = emptyBorder
        totalContributionsValue.text = ""
        totalContributionsValue.font = Font("Serif", Font.PLAIN, fontSize)
        totalContributionsValue.border = emptyBorder
        contributionPanel.layout = BorderLayout()
        contributionPanel.add(totalContributionsLabel, BorderLayout.WEST)
        contributionPanel.add(totalContributionsValue, BorderLayout.EAST)

        monthlyContributionPanel.add(sssPanel)
        monthlyContributionPanel.add(pagibigPanel)
        monthlyContributionPanel.add(philhealthPanel)
        monthlyContributionPanel.add(contributionPanel)

        centerPanel.add(monthlyContributionPanel, BorderLayout.WEST)
    }

    private fun initializeMonthlyIncomeTaxPanel() {
        incomeTaxCalculationPanel.layout = GridLayout(2, 1)
        incomeTaxCalculationPanel.border = fullBorder

        taxableIncomeLabel.text = "Taxable Income"
        taxableIncomeLabel.font = Font("Serif", Font.PLAIN, fontSize)
        taxableIncomeLabel.border = emptyBorder
        taxableIncomeValue.text = ""
        taxableIncomeValue.font = Font("Serif", Font.PLAIN, fontSize)
        taxableIncomeValue.border = emptyBorder
        taxableIncomePanel.layout = BorderLayout()
        taxableIncomePanel.add(taxableIncomeLabel, BorderLayout.WEST)
        taxableIncomePanel.add(taxableIncomeValue, BorderLayout.EAST)

        incomeTaxLabel.text = "Income Tax"
        incomeTaxLabel.font = Font("Serif", Font.PLAIN, fontSize)
        incomeTaxLabel.border = emptyBorder
        incomeTaxValue.text = ""
        incomeTaxValue.font = Font("Serif", Font.PLAIN, fontSize)
        incomeTaxValue.border = emptyBorder
        incomeTaxPanel.layout = BorderLayout()
        incomeTaxPanel.add(incomeTaxLabel, BorderLayout.WEST)
        incomeTaxPanel.add(incomeTaxValue, BorderLayout.EAST)

        incomeTaxCalculationPanel.add(taxableIncomePanel)
        incomeTaxCalculationPanel.add(incomeTaxPanel)

        centerPanel.add(incomeTaxCalculationPanel)
    }

    private fun initializeFinalBreakdownPanel() {
        breakdownPanel.layout = GridLayout(2, 1)
        breakdownPanel.border = fullBorder

        totalDeductionsLabel.text = "Total Deductions"
        totalDeductionsLabel.font = Font("Serif", Font.PLAIN, fontSize)
        totalDeductionsLabel.border = emptyBorder
        totalDeductionsValue.text = ""
        totalDeductionsValue.font = Font("Serif", Font.PLAIN, fontSize)
        totalDeductionsValue.border = emptyBorder
        totalDeductionsPanel.layout = BorderLayout()
        totalDeductionsPanel.add(totalDeductionsLabel, BorderLayout.WEST)
        totalDeductionsPanel.add(totalDeductionsValue, BorderLayout.EAST)

        netPayLabel.text = "Net Pay"
        netPayLabel.font = Font("Serif", Font.PLAIN, fontSize)
        netPayLabel.border = emptyBorder
        netPayValue.text = ""
        netPayValue.font = Font("Serif", Font.PLAIN, fontSize)
        netPayValue.border = emptyBorder
        netPayPanel.layout = BorderLayout()
        netPayPanel.add(netPayLabel, BorderLayout.WEST)
        netPayPanel.add(netPayValue, BorderLayout.EAST)

        breakdownPanel.add(totalDeductionsPanel)
        breakdownPanel.add(netPayPanel)

        this.add(breakdownPanel, BorderLayout.SOUTH)
    }

    public fun updateDisplay(sSS: BigDecimal, pagIbig: BigDecimal, philHealth: BigDecimal, contributions: BigDecimal, taxable: BigDecimal, incomeTax: BigDecimal, deductions: BigDecimal, netPay: BigDecimal) {
        sssValue.text = sSS.toString()
        pagibigValue.text = pagIbig.toString()
        philhealthValue.text = philHealth.toString()
        totalContributionsValue.text = contributions.toString()

        taxableIncomeValue.text = taxable.toString()
        incomeTaxValue.text = incomeTax.toString()

        totalDeductionsValue.text = deductions.toString()
        netPayValue.text = netPay.toString()
    }

    public fun updateDisplay() {
        sssValue.text = ""
        pagibigValue.text = ""
        philhealthValue.text = ""
        totalContributionsValue.text = ""

        taxableIncomeValue.text = ""
        incomeTaxValue.text = ""

        totalDeductionsValue.text = ""
        netPayValue.text = ""
    }

    public fun setKeyListener(listener: KeyListener) {
        this.textField.addKeyListener(listener)
    }

    public fun setActionListener(listener: ActionListener) {
        this.submitButton.addActionListener(listener)
    }
}