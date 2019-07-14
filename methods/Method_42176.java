public void load(ErrorCause errorCause){
  title.setText(errorCause.getTitle());
  causes.removeAllViews();
  for (  String c : errorCause.getCauses()) {
    ThemedTextView textView=new ThemedTextView(itemView.getContext());
    textView.setStyleColor(ThemedTextView.SUB_TEXT_COLOR);
    textView.setText(c);
    causes.addView(textView);
  }
}
