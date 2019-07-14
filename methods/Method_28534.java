public SpannableBuilder clickable(final CharSequence text,final View.OnClickListener listener){
  if (!InputHelper.isEmpty(text))   return append(text,new ClickableSpan(){
    @Override public void updateDrawState(    TextPaint ds){
      ds.setColor(ds.linkColor);
      ds.setUnderlineText(false);
    }
    @Override public void onClick(    View widget){
      listener.onClick(widget);
    }
  }
);
  return this;
}
