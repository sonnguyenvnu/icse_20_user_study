public static TtmlNode buildTextNode(String text){
  return new TtmlNode(null,TtmlRenderUtil.applyTextElementSpacePolicy(text),C.TIME_UNSET,C.TIME_UNSET,null,null,ANONYMOUS_REGION_ID,null);
}
