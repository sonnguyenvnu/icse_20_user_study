private static void unsetRotationX(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isRotationXSet() && view.getRotationX() != 0) {
      view.setRotationX(0);
    }
  }
}
