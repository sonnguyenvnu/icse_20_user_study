@Override public void unmountView(JSONObject json,View view){
  if (view instanceof IContainer) {
    ViewBase vb=((IContainer)view).getVirtualView();
    vb.reset();
  }
}
