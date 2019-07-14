public static @NonNull Project mxCurrencyCAProject(){
  return project().toBuilder().name("mxCurrencyCAProject").country("CA").currentCurrency("MXN").currencySymbol("$").currency("CAD").staticUsdRate(0.75f).fxRate(.75f).build();
}
