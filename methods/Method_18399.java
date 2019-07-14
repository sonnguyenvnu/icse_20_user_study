static void setComponentTouchListener(View v,ComponentTouchListener listener){
  if (v instanceof ComponentHost) {
    ((ComponentHost)v).setComponentTouchListener(listener);
  }
 else {
    v.setOnTouchListener(listener);
    v.setTag(R.id.component_touch_listener,listener);
  }
}
