public static Set<String> combineNER(String[] nerArray,NERTagSet tagSet){
  Set<String> result=new LinkedHashSet<String>();
  int begin=0;
  String prePos=NERTagSet.posOf(nerArray[0]);
  for (int i=1; i < nerArray.length; i++) {
    if (nerArray[i].charAt(0) == tagSet.B_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR || nerArray[i].charAt(0) == tagSet.O_TAG_CHAR) {
      if (i - begin > 1)       result.add(String.format("%d\t%d\t%s",begin,i,prePos));
      begin=i;
    }
    prePos=NERTagSet.posOf(nerArray[i]);
  }
  if (nerArray.length - 1 - begin > 1) {
    result.add(String.format("%d\t%d\t%s",begin,nerArray.length,prePos));
  }
  return result;
}
