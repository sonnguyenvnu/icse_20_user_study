@Override public boolean validate(String value){
  if (!valid) {
    return true;
  }
  Matcher matcher=pattern.matcher(value);
  return (matcher.matches());
}
