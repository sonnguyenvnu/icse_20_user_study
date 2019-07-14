@JsonAnySetter public void setCurrencyAmount(String currencyBalance,BigDecimal amount){
  String[] parts=currencyBalance.split("_");
  Currency currency=Currency.getInstance(parts[0].toUpperCase());
  if (parts.length > 1) {
switch (parts[1]) {
case "reserved":
      this.currencyReserved.put(currency,amount);
    break;
case "available":
  this.currencyAvailable.put(currency,amount);
break;
case "balance":
this.currencyBalance.put(currency,amount);
break;
}
if (!currencies.contains(currency)) currencies.add(currency);
}
}
