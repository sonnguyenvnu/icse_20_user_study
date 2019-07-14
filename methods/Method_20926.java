public static @NonNull Project mxProject(){
  return project().toBuilder().name("mxProject").country("MX").currentCurrency("MXN").currencySymbol("$").currency("MXN").staticUsdRate(0.75f).fxRate(0.75f).build();
}
