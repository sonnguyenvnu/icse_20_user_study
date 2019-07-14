private static void unsetViewTag(View view){
  if (view instanceof ComponentHost) {
    final ComponentHost host=(ComponentHost)view;
    host.setViewTag(null);
  }
 else {
    view.setTag(null);
  }
}
