public String[] segment(String text){
  if (text.length() == 0)   return new String[0];
  char[] charArray=text.toCharArray();
  CharTable.normalization(charArray);
  List<int[]> atomList=new LinkedList<int[]>();
  int start=0;
  int end=charArray.length;
  int offsetAtom=start;
  byte preType=CharType.get(charArray[offsetAtom]);
  byte curType;
  while (++offsetAtom < end) {
    curType=CharType.get(charArray[offsetAtom]);
    if (preType == CharType.CT_CHINESE) {
      atomList.add(new int[]{start,offsetAtom - start});
      start=offsetAtom;
    }
 else     if (curType != preType) {
      if (charArray[offsetAtom] == '.' && preType == CharType.CT_NUM) {
        while (++offsetAtom < end) {
          curType=CharType.get(charArray[offsetAtom]);
          if (curType != CharType.CT_NUM)           break;
        }
      }
      if (preType == CharType.CT_NUM || preType == CharType.CT_LETTER)       atomList.add(new int[]{start,offsetAtom - start});
      start=offsetAtom;
    }
    preType=curType;
  }
  if (offsetAtom == end)   if (preType == CharType.CT_NUM || preType == CharType.CT_LETTER)   atomList.add(new int[]{start,offsetAtom - start});
  if (atomList.isEmpty())   return new String[0];
  String[] termArray=new String[atomList.size() - 1];
  Iterator<int[]> iterator=atomList.iterator();
  int[] pre=iterator.next();
  int p=-1;
  while (iterator.hasNext()) {
    int[] cur=iterator.next();
    termArray[++p]=new StringBuilder(pre[1] + cur[1]).append(charArray,pre[0],pre[1]).append(charArray,cur[0],cur[1]).toString();
    pre=cur;
  }
  return termArray;
}
