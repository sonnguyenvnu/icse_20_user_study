public List<Word> parse(String title){
  List<Word> words=new LinkedList<>();
  boolean[] isProtected=determineProtectedChars(title);
  reset();
  int index=0;
  for (  char c : title.toCharArray()) {
    if (Character.isWhitespace(c)) {
      createWord(isProtected).ifPresent(words::add);
    }
 else {
      if (wordStart == -1) {
        wordStart=index;
      }
      buffer.append(c);
    }
    index++;
  }
  createWord(isProtected).ifPresent(words::add);
  return words;
}
