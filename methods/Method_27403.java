public void beforeChildren(TagNode node,SpannableStringBuilder builder){
  if (builder.length() > 0 && builder.charAt(builder.length() - 1) != 10) {
    this.appendNewLine(builder);
  }
}
