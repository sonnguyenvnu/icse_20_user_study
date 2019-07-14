public String rewriteQuickly(String text){
  assert text != null;
  StringBuilder sbOut=new StringBuilder((int)(text.length() * 1.2));
  String preWord=Predefine.TAG_BIGIN;
  for (int i=0; i < text.length(); ++i) {
    int state=1;
    state=trie.transition(text.charAt(i),state);
    if (state > 0) {
      int start=i;
      int to=i + 1;
      int end=-1;
      SynonymItem value=null;
      for (; to < text.length(); ++to) {
        state=trie.transition(text.charAt(to),state);
        if (state < 0)         break;
        SynonymItem output=trie.output(state);
        if (output != null) {
          value=output;
          end=to + 1;
        }
      }
      if (value != null) {
        Synonym synonym=value.randomSynonym(Type.EQUAL,preWord);
        if (synonym != null) {
          sbOut.append(synonym.realWord);
          preWord=synonym.realWord;
        }
 else {
          preWord=text.substring(start,end);
          sbOut.append(preWord);
        }
        i=end - 1;
      }
 else {
        preWord=String.valueOf(text.charAt(i));
        sbOut.append(text.charAt(i));
      }
    }
 else {
      preWord=String.valueOf(text.charAt(i));
      sbOut.append(text.charAt(i));
    }
  }
  return sbOut.toString();
}
