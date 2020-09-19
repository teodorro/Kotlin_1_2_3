const val standardDiscount = 100_00;
const val largeSumDiscount = 0.05;
const val regularCustomerDiscount = 0.01;
const val threshold1 = 1000_00;
const val threshold2 = 10000_00;

fun main() {
    printDiscounts(50_00, 1001_33, true);
    printDiscounts(1050_00, 1002_33, true);
    printDiscounts(100500_00, 1003_33, true);
    printDiscounts(50_00, 1004_36, false);
    printDiscounts(1050_00, 1005_36, false);
    printDiscounts(100500_00, 1006_36, false);
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

    val previousBuyPriceStr = getMoneyString(previousBuyPrice);
    val nextStandardPriceStr = getMoneyString(nextStandardPrice);
    val nextPriceAfterPreviousBuyDiscountStr = getMoneyString(nextPriceAfterPreviousBuyDiscount);
    val nextPriceAfterRegularCustomerDiscountStr = getMoneyString(nextPriceAfterRegularCustomerDiscount);
    println(
        (if (isRegularCustomer) "Постоянный покупатель\n" else "Случайный покупатель\n")
                + "Предыдущие покупки: $previousBuyPriceStr\n"
                + "Следующая покупка: $nextStandardPriceStr\n"
                + (if (previousBuyDiscount > 0) "После $previousBuyDiscountStr скидки: $nextPriceAfterPreviousBuyDiscountStr\n" else "")
                + (if (isRegularCustomer) "После $regularCustomerDiscountStr скидки: $nextPriceAfterRegularCustomerDiscountStr\n" else "")
    );
}

fun getMoneyString(money: Int): String{
    val kop = money % 100
    val rub = money / 100;
    val kopStr = getKopStr(kop)
    if (rub == 0)
        return kopStr;
    val rubStr = getRubStr(rub)
    return "$rubStr $kopStr";
}

fun getRubStr(rub: Int): String {
    if (rub == 0)
        return "";
    val last = rub % 10;
    val ending = when {
        last == 1 && rub != 1 -> "рубль";
        last in 2..4 && (rub < 10 || rub > 19) -> "рубля";
        else -> "рублей"
    }
    return "$rub $ending";
}

fun getKopStr(kop: Int): String {
    if (kop == 0)
        return "";
    val last = kop % 10;
    val ending = when {
        last == 1 && kop != 1 -> "копейка";
        last in 2..4 && (kop < 10 || kop > 19) -> "копейки";
        else -> "копеек"
    }
    return "$kop $ending";
}


