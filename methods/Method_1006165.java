@Override public String format(String text){
  Objects.requireNonNull(text);
  if (text.isEmpty()) {
    return text;
  }
  String result=text.replaceAll("([0-9,\\.]+)-([Bb][Ii][Tt])","$1\\\\mbox\\{-\\}$2");
  result=result.replaceAll("([0-9,\\.]+) ([Bb][Ii][Tt])","$1~$2");
  for (  String listOfWord : prefixUnitCombinations) {
    result=result.replaceAll("([0-9])(" + listOfWord + ")","$1\\{$2\\}");
    result=result.replaceAll("([0-9])-(" + listOfWord + ")","$1\\\\mbox\\{-\\}\\{$2\\}");
    result=result.replaceAll("([0-9]) (" + listOfWord + ")","$1~\\{$2\\}");
  }
  return result;
}
