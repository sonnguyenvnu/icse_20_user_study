private static void unsetAccessibilityDelegate(View view){
  if (!(view instanceof ComponentHost) && view.getTag(R.id.component_node_info) == null) {
    return;
  }
  view.setTag(R.id.component_node_info,null);
  if (!(view instanceof ComponentHost)) {
    ViewCompat.setAccessibilityDelegate(view,null);
  }
}
