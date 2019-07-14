private static void unsetAlpha(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isAlphaSet() && view.getAlpha() != 1) {
      view.setAlpha(1);
    }
  }
}
