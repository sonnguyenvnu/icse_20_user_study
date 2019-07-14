private static void unsetRotation(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isRotationSet() && view.getRotation() != 0) {
      view.setRotation(0);
    }
  }
}
