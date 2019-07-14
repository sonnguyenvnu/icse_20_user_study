public void stopAnim(){
  for (int j=0; j < listView.getChildCount(); j++) {
    View v=listView.getChildAt(j);
    if (v != null)     v.clearAnimation();
  }
}
