/** 
 * ????
 * @param charArray   ????
 * @param wordNet     ????
 * @param natureArray ????
 */
protected void posTag(char[] charArray,int[] wordNet,Nature[] natureArray){
  if (config.speechTagging) {
    for (int i=0; i < natureArray.length; ) {
      if (natureArray[i] == null) {
        int j=i + 1;
        for (; j < natureArray.length; ++j) {
          if (natureArray[j] != null)           break;
        }
        List<AtomNode> atomNodeList=quickAtomSegment(charArray,i,j);
        for (        AtomNode atomNode : atomNodeList) {
          if (atomNode.sWord.length() >= wordNet[i]) {
            wordNet[i]=atomNode.sWord.length();
            natureArray[i]=atomNode.getNature();
            i+=wordNet[i];
          }
        }
        i=j;
      }
 else {
        ++i;
      }
    }
  }
}
