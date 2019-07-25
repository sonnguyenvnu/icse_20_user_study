@NotNull public static Quote fetch(String promoter){
  Quote quote=quoteByPromoter.get(promoter);
  if (quote == null) {
    throw new IllegalArgumentException("No Quote promoted by " + promoter);
  }
  return quote;
}
