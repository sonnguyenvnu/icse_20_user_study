@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  if (isPre) {
    StringBuffer buffer=new StringBuffer();
    buffer.append("\n");
    getPlainText(buffer,node);
    buffer.append("\n");
    builder.append(replace(buffer.toString()));
    builder.append("\n");
    builder.setSpan(new CodeBackgroundRoundedSpan(color),start,builder.length(),SPAN_EXCLUSIVE_EXCLUSIVE);
    builder.append("\n");
    this.appendNewLine(builder);
    this.appendNewLine(builder);
  }
 else {
    StringBuffer text=node.getText();
    builder.append(" ");
    builder.append(replace(text.toString()));
    builder.append(" ");
    final int stringStart=start + 1;
    final int stringEnd=builder.length() - 1;
    builder.setSpan(new BackgroundColorSpan(color),stringStart,stringEnd,SPAN_EXCLUSIVE_EXCLUSIVE);
    if (theme == PrefGetter.LIGHT) {
      builder.setSpan(new ForegroundColorSpan(Color.RED),stringStart,stringEnd,SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    builder.setSpan(new TypefaceSpan("monospace"),stringStart,stringEnd,SPAN_EXCLUSIVE_EXCLUSIVE);
  }
}
