private LayoutElementParcelable getBackElement(){
  if (back == null) {
    back=new LayoutElementParcelable(true,getString(R.string.goback),getBoolean(PREFERENCE_SHOW_THUMB));
  }
  return back;
}
