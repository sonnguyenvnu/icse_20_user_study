public static CharSequence applyEntities(String text,List<TextEntity> entityList){
  if (TextUtils.isEmpty(text) || entityList.isEmpty()) {
    return text;
  }
  SpannableStringBuilder builder=new SpannableStringBuilder();
  int lastTextIndex=0;
  for (  TextEntity entity : entityList) {
    if (entity.start < 0 || entity.end < entity.start) {
      LogUtils.w("Ignoring malformed entity " + entity);
      continue;
    }
    if (entity.start < lastTextIndex) {
      LogUtils.w("Ignoring backward entity " + entity + ", with lastTextIndex=" + lastTextIndex);
      continue;
    }
    int entityStart=text.offsetByCodePoints(lastTextIndex,entity.start - lastTextIndex);
    int entityEnd=text.offsetByCodePoints(entityStart,entity.end - entity.start);
    builder.append(text.substring(lastTextIndex,entityStart));
    String entityText=entity.title;
    if (!Settings.SHOW_LONG_URL_FOR_LINK_ENTITY.getValue() && URLUtil.isNetworkUrl(entityText) && Patterns.WEB_URL.matcher(entityText).matches()) {
      entityText=text.substring(entityStart,entityEnd);
    }
    int entityStartInAppliedText=builder.length();
    builder.append(entityText).setSpan(new UriSpan(entity.href),entityStartInAppliedText,entityStartInAppliedText + entityText.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    lastTextIndex=entityEnd;
  }
  if (lastTextIndex != text.length()) {
    builder.append(text.substring(lastTextIndex,text.length()));
  }
  return builder;
}
