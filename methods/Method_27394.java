@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  String src=node.getAttributeByName("src");
  if (!InputHelper.isEmpty(src)) {
    if (!PrefGetter.isAutoImageDisabled()) {
      builder.append("?");
      if (isNull())       return;
      builder.append("\n");
      DrawableGetter imageGetter=new DrawableGetter(textView,width);
      builder.setSpan(new ImageSpan(imageGetter.getDrawable(src)),start,builder.length(),SPAN_EXCLUSIVE_EXCLUSIVE);
      builder.setSpan(new CenterSpan(),start,builder.length(),SPAN_EXCLUSIVE_EXCLUSIVE);
      builder.append("\n");
    }
 else {
      builder.append(SpannableBuilder.builder().clickable("Image",v -> SchemeParser.launchUri(v.getContext(),src)));
      builder.append("\n");
    }
  }
}
