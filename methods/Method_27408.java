@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  builder.setSpan(new StrikethroughSpan(),start,end,33);
}
