public String replace(Matcher matcher){
  return matcher.group(1) + matcher.group(2).toUpperCase();
}
