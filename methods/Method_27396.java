@Override public void handleTagNode(TagNode tagNode,SpannableStringBuilder spannableStringBuilder,int i,int i1){
  spannableStringBuilder.append("\n");
  SpannableStringBuilder builder=new SpannableStringBuilder("$");
  HrSpan hrSpan=new HrSpan(color,width);
  builder.setSpan(hrSpan,0,builder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  builder.setSpan(new CenterSpan(),0,builder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  builder.append("\n");
  spannableStringBuilder.append(builder);
}
