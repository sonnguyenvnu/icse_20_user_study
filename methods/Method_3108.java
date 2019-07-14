/** 
 * @param tonePinyinText
 * @return
 */
public static List<Pinyin> convert(String tonePinyinText,boolean removeNull){
  List<Pinyin> pinyinList=new LinkedList<Pinyin>();
  Collection<Token> tokenize=trie.tokenize(tonePinyinText);
  for (  Token token : tokenize) {
    Pinyin pinyin=mapKey.get(token.getFragment());
    if (removeNull && pinyin == null)     continue;
    pinyinList.add(pinyin);
  }
  return pinyinList;
}
