private void process(String text){
  text=HtmlConverter.convertHtmlToString(text);
  if (title) {
    if (page.title == null) {
      page.title=text;
    }
 else {
      page.title=page.title + " " + text;
    }
  }
  int weight;
  if (title) {
    weight=Weight.TITLE;
  }
 else   if (heading) {
    weight=Weight.HEADER;
  }
 else {
    weight=Weight.PARAGRAPH;
  }
  StringTokenizer t=new StringTokenizer(text," \t\r\n\"'.,:;!&/\\?%@`[]{}()+-=<>|*^~#$" + (char)160,false);
  while (t.hasMoreTokens()) {
    String token=t.nextToken();
    if (token.length() < MIN_WORD_SIZE) {
      continue;
    }
    if (Character.isDigit(token.charAt(0))) {
      continue;
    }
    String lower=StringUtils.toLowerEnglish(token);
    Word word=words.get(lower);
    if (word == null) {
      word=new Word(token);
      words.put(lower,word);
    }
 else     if (!word.name.equals(token)) {
      word.name=token.compareTo(word.name) > 0 ? token : word.name;
    }
    page.totalWeight+=weight;
    word.addPage(page,weight);
  }
}
