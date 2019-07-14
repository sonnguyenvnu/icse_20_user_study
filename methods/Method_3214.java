protected String nextWord(){
  String word=scanner.next();
  return word == null || word.length() == 0 || word.equals("q") ? null : word;
}
