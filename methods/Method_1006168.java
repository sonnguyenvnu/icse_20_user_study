private String format(String text,List<String> listOfWords){
  String result=text;
  listOfWords.sort(new StringLengthComparator());
  for (  String listOfWord : listOfWords) {
    result=result.replaceAll("(^|[- /\\[(}\"])" + listOfWord + "($|[^a-zA-Z}])","$1\\{" + listOfWord + "\\}$2");
  }
  return result;
}
