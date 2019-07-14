static void setComponentClickListener(View v,ComponentClickListener listener){
  if (v instanceof ComponentHost) {
    ((ComponentHost)v).setComponentClickListener(listener);
  }
 else {
    v.setOnClickListener(listener);
    v.setTag(R.id.component_click_listener,listener);
  }
}
