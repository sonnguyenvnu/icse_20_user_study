public SpannableBuilder url(final CharSequence text,final View.OnClickListener listener){
  if (!InputHelper.isEmpty(text))   return append(text,new URLSpan(text.toString()){
    @Override public void onClick(    View widget){
      listener.onClick(widget);
    }
  }
);
  return this;
}
