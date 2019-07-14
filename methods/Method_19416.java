private String getDebugInfo(){
  StringBuilder debugInfo=new StringBuilder();
  debugInfo.append(" [");
  debugInfo.append(mContextLogTag);
  debugInfo.append("] ");
  if (mText instanceof SpannableStringBuilder) {
    Object[] spans=((SpannableStringBuilder)mText).getSpans(0,mText.length(),Object.class);
    debugInfo.append("spans: ");
    for (    Object span : spans) {
      debugInfo.append(span.getClass().getSimpleName());
      debugInfo.append(", ");
    }
  }
  debugInfo.append("ellipsizedWidth: ");
  debugInfo.append(mLayout.getEllipsizedWidth());
  debugInfo.append(", lineCount: ");
  debugInfo.append(mLayout.getLineCount());
  return debugInfo.toString();
}
