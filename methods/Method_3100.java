protected static List<Pinyin> segLongest(char[] charArray,AhoCorasickDoubleArrayTrie<Pinyin[]> trie,boolean remainNone){
  final Pinyin[][] wordNet=new Pinyin[charArray.length][];
  trie.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<Pinyin[]>(){
    @Override public void hit(    int begin,    int end,    Pinyin[] value){
      int length=end - begin;
      if (wordNet[begin] == null || length > wordNet[begin].length) {
        wordNet[begin]=length == 1 ? new Pinyin[]{value[0]} : value;
      }
    }
  }
);
  List<Pinyin> pinyinList=new ArrayList<Pinyin>(charArray.length);
  for (int offset=0; offset < wordNet.length; ) {
    if (wordNet[offset] == null) {
      if (remainNone) {
        pinyinList.add(Pinyin.none5);
      }
      ++offset;
      continue;
    }
    for (    Pinyin pinyin : wordNet[offset]) {
      pinyinList.add(pinyin);
    }
    offset+=wordNet[offset].length;
  }
  return pinyinList;
}
