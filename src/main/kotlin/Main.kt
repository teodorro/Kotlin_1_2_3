const val standardDiscount = 100;
const val largeSumDiscount = 0.05;
const val regularCustomerDiscount = 0.01;
const val threshold1 = 1000;
const val threshold2 = 10000;

fun main() {
    printDiscounts(50, 1000, true);
    printDiscounts(1050, 1000, true);
    printDiscounts(100500, 1000, true);
    printDiscounts(50, 1000, false);
    printDiscounts(1050, 1000, false);
    printDiscounts(100500, 1000, false);
}

fun printDiscounts(previousBuyPrice: Int, nextStandardPrice: Int, isRegularCustomer: Boolean) {

    val previousBuyDiscount = when {
        previousBuyPrice <= threshold1 -> 0
        previousBuyPrice <= threshold2 -> standardDiscount
        else -> (largeSumDiscount * nextStandardPrice).toInt()
    }
    val nextPriceAfterPreviousBuyDiscount = nextStandardPrice - previousBuyDiscount;
    val previousBuyDiscountStr = when {
        previousBuyPrice <= threshold1 -> ""
        previousBuyPrice <= threshold2 -> "100 руб"
        else -> "5%"
    }
    val nextPriceAfterRegularCustomerDiscount = if (isRegularCustomer)
        (nextPriceAfterPreviousBuyDiscount * (1 - regularCustomerDiscount)).toInt() else
        nextPriceAfterPreviousBuyDiscount;
    val regularCustomerDiscountStr = if (isRegularCustomer) "1%" else "";
    println(
        (if (isRegularCustomer) "Постоянный покупатель\n" else "Случайный покупатель\n")
                + "Предыдущие покупки: $previousBuyPrice руб\n"
                + "Следующая покупка: $nextStandardPrice руб\n"
                + (if (previousBuyDiscount > 0) "После $previousBuyDiscountStr скидки: $nextPriceAfterPreviousBuyDiscount руб\n" else "")
                + (if (isRegularCustomer) "После $regularCustomerDiscountStr скидки: $nextPriceAfterRegularCustomerDiscount руб\n" else "")
    );

}


