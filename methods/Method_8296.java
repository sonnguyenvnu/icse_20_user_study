@Override public void finishFragment(){
  super.finishFragment();
  if (scrimPopupWindow != null) {
    scrimPopupWindow.dismiss();
  }
}
