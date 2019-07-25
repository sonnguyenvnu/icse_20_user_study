@Override public String format(String text){
  Objects.requireNonNull(text);
  if (text.isEmpty()) {
    return text;
  }
  return this.format(text,this.protectedTermsLoader.getProtectedTerms());
}
