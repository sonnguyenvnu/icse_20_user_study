public static String adaptXchangeCurrency(Currency xchangeSymbol){
  if (xchangeSymbol == null) {
    return null;
  }
  return xchangeSymbol.toString().toLowerCase();
}
