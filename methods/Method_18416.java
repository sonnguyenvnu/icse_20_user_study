private static void setRotationX(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isRotationXSet()) {
      view.setRotationX(nodeInfo.getRotationX());
    }
  }
}
