private void setTypeface(final @NonNull TextView textView,final boolean bold){
  final int style=bold ? Typeface.BOLD : Typeface.NORMAL;
  textView.setTypeface(Typeface.create(textView.getTypeface(),style));
}
