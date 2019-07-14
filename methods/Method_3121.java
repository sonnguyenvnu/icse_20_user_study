protected static String segLongest(char[] charArray,AhoCorasickDoubleArrayTrie<String> trie){
  final String[] wordNet=new String[charArray.length];
  final int[] lengthNet=new int[charArray.length];
  trie.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<String>(){
    @Override public void hit(    int begin,    int end,    String value){
      int length=end - begin;
      if (length > lengthNet[begin]) {
        wordNet[begin]=value;
        lengthNet[begin]=length;
      }
    }
  }
);
  StringBuilder sb=new StringBuilder(charArray.length);
  for (int offset=0; offset < wordNet.length; ) {
    if (wordNet[offset] == null) {
      sb.append(charArray[offset]);
      ++offset;
      continue;
    }
    sb.append(wordNet[offset]);
    offset+=lengthNet[offset];
  }
  return sb.toString();
}
