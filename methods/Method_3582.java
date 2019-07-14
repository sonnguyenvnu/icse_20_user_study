@Override protected PinyinKey generateKey(String sentence){
  PinyinKey pinyinKey=new PinyinKey(sentence);
  if (pinyinKey.size() == 0)   return null;
  return pinyinKey;
}
