private void onToggle(boolean expanded,boolean animate){
  if (!expanded) {
    minimized.setVisibility(View.GONE);
    patch.setText("");
    name.setMaxLines(2);
    toggle.setRotation(0.0f);
  }
 else {
    minimized.setVisibility(View.VISIBLE);
    name.setMaxLines(5);
    setPatchText(pathText);
    toggle.setRotation(180f);
  }
}
