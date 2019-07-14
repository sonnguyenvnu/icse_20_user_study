private void reuseView(ViewGroup view){
  if (view == null) {
    return;
  }
  int tag=(Integer)view.getTag();
  view.setVisibility(View.GONE);
  if (tag == 1) {
    textViews.add(view);
  }
 else   if (tag == 2) {
    imageViews.add(view);
  }
 else   if (tag == 3) {
    audioViews.add(view);
  }
}
