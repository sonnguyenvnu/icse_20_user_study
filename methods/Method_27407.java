@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  builder.setSpan(new MarkDownQuoteSpan(color),start + 1,builder.length(),33);
}
