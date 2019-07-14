public String formatCurrencyDecimalString(long amount,String type,boolean inludeType){
  type=type.toUpperCase();
  String customFormat;
  double doubleAmount;
  amount=Math.abs(amount);
switch (type) {
case "CLF":
    customFormat=" %.4f";
  doubleAmount=amount / 10000.0;
break;
case "IRR":
doubleAmount=amount / 100.0f;
if (amount % 100 == 0) {
customFormat=" %.0f";
}
 else {
customFormat=" %.2f";
}
break;
case "BHD":
case "IQD":
case "JOD":
case "KWD":
case "LYD":
case "OMR":
case "TND":
customFormat=" %.3f";
doubleAmount=amount / 1000.0;
break;
case "BIF":
case "BYR":
case "CLP":
case "CVE":
case "DJF":
case "GNF":
case "ISK":
case "JPY":
case "KMF":
case "KRW":
case "MGA":
case "PYG":
case "RWF":
case "UGX":
case "UYI":
case "VND":
case "VUV":
case "XAF":
case "XOF":
case "XPF":
customFormat=" %.0f";
doubleAmount=amount;
break;
case "MRO":
customFormat=" %.1f";
doubleAmount=amount / 10.0;
break;
default :
customFormat=" %.2f";
doubleAmount=amount / 100.0;
break;
}
return String.format(Locale.US,inludeType ? type : "" + customFormat,doubleAmount).trim();
}
