@Override public String format(String value){
  Objects.requireNonNull(value);
  value=LINEBREAKS.matcher(value).replaceAll(" ");
  return value.trim();
}
