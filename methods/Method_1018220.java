/** 
 * @see ISegment#next()
 * @return  IWord or null
 */
@Override public IWord next() throws IOException {
  if (wordPool.size() > 0) {
    return wordPool.remove();
  }
  int c, i, pos;
  IWord w=null;
  String T=null;
  while ((c=readNext()) != -1) {
    w=null;
    T=null;
    pos=idx;
    isb.clear();
    if (StringUtil.isHWEnChar(c) || StringUtil.isFWEnChar(c)) {
      if (c > 65280)       c-=65248;
      if (c >= 65 && c <= 90)       c+=32;
    }
    isb.append((char)c);
    T=isb.toString();
    if (dic.match(ILexicon.CJK_WORD,T)) {
      w=dic.get(ILexicon.CJK_WORD,T);
    }
    for (i=1; i < config.MAX_LENGTH; i++) {
      c=readNext();
      if (c == -1) {
        break;
      }
      if (StringUtil.isHWEnChar(c) || StringUtil.isFWEnChar(c)) {
        if (c > 65280)         c-=65248;
        if (c >= 65 && c <= 90)         c+=32;
      }
      isb.append((char)c);
      T=isb.toString();
      if (dic.match(ILexicon.CJK_WORD,T)) {
        w=dic.get(ILexicon.CJK_WORD,T);
      }
    }
    if (w == null) {
      for (i=isb.length() - 1; i > 0; i--)       pushBack(isb.charAt(i));
      continue;
    }
    int LENGTH=w.getLength();
    for (i=isb.length() - 1; i >= LENGTH; i--) {
      pushBack(isb.charAt(i));
    }
    w=w.clone();
    w.setPosition(pos);
    if (config.APPEND_CJK_PINYIN && config.LOAD_CJK_PINYIN && w.getPinyin() != null) {
      IWord pinyin=new Word(w.getPinyin(),IWord.T_CJK_PINYIN);
      pinyin.setPosition(pos);
      wordPool.add(pinyin);
    }
    if (config.APPEND_CJK_SYN && config.LOAD_CJK_SYN && w.getSyn() != null) {
      SegKit.appendSynonyms(wordPool,w);
    }
    return w;
  }
  return null;
}
