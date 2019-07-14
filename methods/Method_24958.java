static private int getNumDigitsAfterPoint(String number){
  Pattern p=Pattern.compile("\\.[0-9]+");
  Matcher m=p.matcher(number);
  if (m.find()) {
    return m.end() - m.start() - 1;
  }
  return 0;
}
