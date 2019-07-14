@Override protected void callAction(String name){
  if (JFX_OPEN_ACTION.equals(name)) {
    show();
  }
 else   if (JFX_CLOSE_ACTION.equals(name)) {
    hide();
  }
 else {
    super.callAction(name);
  }
}
