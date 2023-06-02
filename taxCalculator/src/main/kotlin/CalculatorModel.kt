import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor

class CalculatorModel {
    /* Variables */
    var sSSContribution: BigDecimal
        private set
    var pagIbigContribution: BigDecimal
        private set
    var philHealthContribution: BigDecimal
        private set
    var monthlyIncome: BigDecimal
        private set
    var taxableIncome: BigDecimal
        private set
    var incomeTax: BigDecimal
        private set
    var netMonthlyPay: BigDecimal
        private set
    var totalContributions: BigDecimal
        private set
    var totalDeductions: BigDecimal
        private set

    /* Constructor */
    constructor() {
        sSSContribution = BigDecimal(0.0)
        pagIbigContribution = BigDecimal(0.0)
        philHealthContribution = BigDecimal(0.0)
        monthlyIncome = BigDecimal(0.0)
        taxableIncome = BigDecimal(0.0)
        incomeTax = BigDecimal(0.0)
        netMonthlyPay = BigDecimal(0.0)
        totalContributions = BigDecimal(0.0)
        totalDeductions = BigDecimal(0.0)
    }

    /* Methods */
    public fun computeTaxBreakdown(grossMonthlyIncome: Double) {
        sSSContribution = computeMonthlySSS(grossMonthlyIncome)
        pagIbigContribution = computeMonthlyPagIbig(grossMonthlyIncome)
        philHealthContribution = computeMonthlyPhilHealth(grossMonthlyIncome)
        monthlyIncome = BigDecimal(grossMonthlyIncome).setScale(2, RoundingMode.HALF_UP)
        totalContributions = (sSSContribution + pagIbigContribution + philHealthContribution).setScale(2, RoundingMode.HALF_UP)
        val intermediateTaxableIncome = monthlyIncome - totalContributions

        if(intermediateTaxableIncome >= BigDecimal(0.0)) {
            taxableIncome = intermediateTaxableIncome.setScale(2, RoundingMode.HALF_UP)
        } else {
            taxableIncome = BigDecimal(0).setScale(2, RoundingMode.HALF_UP)
        }
        incomeTax = computeMonthlyIncomeTax(taxableIncome.toDouble())
        totalDeductions = (incomeTax + sSSContribution + pagIbigContribution + philHealthContribution).setScale(2, RoundingMode.HALF_UP)
        netMonthlyPay = monthlyIncome - totalDeductions
    }

    private fun computeMonthlyIncomeTax(monthlyTaxableIncome: Double): BigDecimal {
        /* Variables */
        val monthlyTax: Double
        val percentages: Array<Double> = arrayOf(0.2, 0.25, 0.3, 0.32, 0.35)
        val flatRate: Array<Double> = arrayOf(0.0, 2500.0, 10833.33, 40833.33, 200833.33)

        /* Function Proper */
        when (monthlyTaxableIncome) {
            in 0.0..20833.0 -> monthlyTax = 0.0
            in 20833.01..33332.99 -> monthlyTax = (flatRate[0] + percentages[0] * (monthlyTaxableIncome - 20833.0))
            in 33333.0..66666.99 -> monthlyTax = (flatRate[1] + percentages[1] * (monthlyTaxableIncome - 33333.0))
            in 66667.0..166666.99 -> monthlyTax = (flatRate[2] + percentages[2] * (monthlyTaxableIncome - 66667.0))
            in 166667.0..666666.99 -> monthlyTax = (flatRate[3] + percentages[3] * (monthlyTaxableIncome - 166667.0))
            else -> monthlyTax = (flatRate[4] + percentages[4] * (monthlyTaxableIncome - 666667.0))
        }

        return BigDecimal(monthlyTax).setScale(2, RoundingMode.HALF_UP)
    }

    private fun computeMonthlySSS(monthlyIncome: Double): BigDecimal {
        /* Variables */
        val baseContribution: Double = 135.0
        val maxContribution: Double = 1125.0
        val sSSContribution: Double;

        /* Function Proper */
        if(monthlyIncome < 3250) {
            sSSContribution = baseContribution
        } else if(monthlyIncome >= 24750.0) {
            sSSContribution = maxContribution
        } else {
            sSSContribution = ((floor((monthlyIncome - 3250) / 500) + 1) * 22.5 + baseContribution)
        }

        return BigDecimal(sSSContribution).setScale(2, RoundingMode.HALF_UP)
    }

    private fun computeMonthlyPagIbig(monthlyIncome: Double): BigDecimal {
        /* Variables */
        val pagIbigContribution: Double;
        val contributionRates: Array<Double> = arrayOf(0.01, 0.02)

        /* Function Proper */
        if(monthlyIncome <= 1500.0) {
            pagIbigContribution = contributionRates[0] * monthlyIncome
        } else {
            if(monthlyIncome > 5000.0) {
                pagIbigContribution = contributionRates[1] * 5000.0
            } else {
                pagIbigContribution = contributionRates[1] * monthlyIncome
            }
        }

        return BigDecimal(pagIbigContribution).setScale(2, RoundingMode.HALF_UP)
    }

    private fun computeMonthlyPhilHealth(monthlyIncome: Double): BigDecimal {
        /* Variables */
        val philHealthContribution: Double
        val premiumRate: Double = 0.04

        /* Function Proper */
        if(monthlyIncome <= 10000) {
            philHealthContribution = 400.0
        } else if(monthlyIncome >= 80000) {
            philHealthContribution = 3200.0
        } else {
            philHealthContribution = monthlyIncome * premiumRate
        }

        return BigDecimal(philHealthContribution).setScale(2, RoundingMode.HALF_UP)
    }
}