protected static String segLongest(char[] charArray,DoubleArrayTrie<String> trie){
  StringBuilder sb=new StringBuilder(charArray.length);
  BaseSearcher searcher=getSearcher(charArray,trie);
  Map.Entry<String,String> entry;
  int p=0;
  int offset;
  while ((entry=searcher.next()) != null) {
    offset=searcher.getOffset();
    while (p < offset) {
      sb.append(charArray[p]);
      ++p;
    }
    sb.append(entry.getValue());
    p=offset + entry.getKey().length();
  }
  while (p < charArray.length) {
    sb.append(charArray[p]);
    ++p;
  }
  return sb.toString();
}
