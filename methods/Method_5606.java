public static TtmlStyle resolveStyle(TtmlStyle style,String[] styleIds,Map<String,TtmlStyle> globalStyles){
  if (style == null && styleIds == null) {
    return null;
  }
 else   if (style == null && styleIds.length == 1) {
    return globalStyles.get(styleIds[0]);
  }
 else   if (style == null && styleIds.length > 1) {
    TtmlStyle chainedStyle=new TtmlStyle();
    for (    String id : styleIds) {
      chainedStyle.chain(globalStyles.get(id));
    }
    return chainedStyle;
  }
 else   if (style != null && styleIds != null && styleIds.length == 1) {
    return style.chain(globalStyles.get(styleIds[0]));
  }
 else   if (style != null && styleIds != null && styleIds.length > 1) {
    for (    String id : styleIds) {
      style.chain(globalStyles.get(id));
    }
    return style;
  }
  return style;
}
