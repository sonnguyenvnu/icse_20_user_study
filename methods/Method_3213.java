protected String[] nextWords(int n,String msg){
  System.out.println(msg + " ('q' to break): ");
  String[] words=new String[n];
  for (int i=0; i < n; i++) {
    String word=nextWord();
    if (word == null)     return null;
    words[i]=word;
  }
  return words;
}
