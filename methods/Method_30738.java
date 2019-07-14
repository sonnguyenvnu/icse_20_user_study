@Override public void setText(CharSequence text,BufferType type){
  super.setText(SpanUtils.addLinks(text),BufferType.SPANNABLE);
}
