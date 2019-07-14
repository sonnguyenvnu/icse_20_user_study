/** 
 * ????
 * @param charArray
 * @param start     ?start??????
 * @param end       ?end??????end?
 * @return ????????start?from???????????
 */
protected static List<AtomNode> atomSegment(char[] charArray,int start,int end){
  List<AtomNode> atomSegment=new ArrayList<AtomNode>();
  int pCur=start, nCurType, nNextType;
  StringBuilder sb=new StringBuilder();
  char c;
  int[] charTypeArray=new int[end - start];
  for (int i=0; i < charTypeArray.length; ++i) {
    c=charArray[i + start];
    charTypeArray[i]=CharType.get(c);
    if (c == '.' && i + start < (charArray.length - 1) && CharType.get(charArray[i + start + 1]) == CharType.CT_NUM)     charTypeArray[i]=CharType.CT_NUM;
 else     if (c == '.' && i + start < (charArray.length - 1) && charArray[i + start + 1] >= '0' && charArray[i + start + 1] <= '9')     charTypeArray[i]=CharType.CT_SINGLE;
 else     if (charTypeArray[i] == CharType.CT_LETTER)     charTypeArray[i]=CharType.CT_SINGLE;
  }
  while (pCur < end) {
    nCurType=charTypeArray[pCur - start];
    if (nCurType == CharType.CT_CHINESE || nCurType == CharType.CT_INDEX || nCurType == CharType.CT_DELIMITER || nCurType == CharType.CT_OTHER) {
      String single=String.valueOf(charArray[pCur]);
      if (single.length() != 0)       atomSegment.add(new AtomNode(single,nCurType));
      pCur++;
    }
 else     if (pCur < end - 1 && ((nCurType == CharType.CT_SINGLE) || nCurType == CharType.CT_NUM)) {
      sb.delete(0,sb.length());
      sb.append(charArray[pCur]);
      boolean reachEnd=true;
      while (pCur < end - 1) {
        nNextType=charTypeArray[++pCur - start];
        if (nNextType == nCurType)         sb.append(charArray[pCur]);
 else {
          reachEnd=false;
          break;
        }
      }
      atomSegment.add(new AtomNode(sb.toString(),nCurType));
      if (reachEnd)       pCur++;
    }
 else {
      atomSegment.add(new AtomNode(charArray[pCur],nCurType));
      pCur++;
    }
  }
  return atomSegment;
}
