private static void unsetScale(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isScaleSet()) {
      if (view.getScaleX() != 1) {
        view.setScaleX(1);
      }
      if (view.getScaleY() != 1) {
        view.setScaleY(1);
      }
    }
  }
}
