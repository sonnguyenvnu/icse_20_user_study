public static String bitflyerProductCode(CurrencyPair pair){
  return pair.toString().replace("/","_");
}
