public static @NonNull Project ukProject(){
  return project().toBuilder().name("ukProject").country("UK").currentCurrency("GBP").currencySymbol("£").currency("GBP").staticUsdRate(1.5f).fxRate(1.5f).build();
}
