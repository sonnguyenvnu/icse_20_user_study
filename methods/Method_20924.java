public static @NonNull Project caProject(){
  return project().toBuilder().name("caProject").country("CA").currentCurrency("CAD").currencySymbol("$").currency("CAD").staticUsdRate(0.75f).fxRate(0.75f).build();
}
