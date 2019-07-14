@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  Table table=getTable(node);
  for (int i=0; i < table.getRows().size(); i++) {
    List<Spanned> row=table.getRows().get(i);
    builder.append("\uFFFC");
    TableRowDrawable drawable=new TableRowDrawable(row,table.isDrawBorder());
    drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
    builder.setSpan(new ImageSpan(drawable),start + i,builder.length(),33);
  }
  builder.append("\uFFFC");
  Drawable drawable=new TableRowDrawable(new ArrayList<Spanned>(),table.isDrawBorder());
  drawable.setBounds(0,0,tableWidth,1);
  builder.setSpan(new ImageSpan(drawable),builder.length() - 1,builder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  builder.setSpan((AlignmentSpan)() -> Alignment.ALIGN_CENTER,start,builder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  builder.append("\n");
}
