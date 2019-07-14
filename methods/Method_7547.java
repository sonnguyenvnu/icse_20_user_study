private void createSubtitleTextView(){
  if (subtitleTextView != null) {
    return;
  }
  subtitleTextView=new SimpleTextView(getContext());
  subtitleTextView.setGravity(Gravity.LEFT);
  subtitleTextView.setVisibility(GONE);
  subtitleTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubtitle));
  addView(subtitleTextView,0,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP));
}
