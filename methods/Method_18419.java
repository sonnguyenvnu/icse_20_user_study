private static void unsetRotationY(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isRotationYSet() && view.getRotationY() != 0) {
      view.setRotationY(0);
    }
  }
}
