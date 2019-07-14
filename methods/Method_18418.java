private static void setRotationY(View view,NodeInfo nodeInfo){
  if (Build.VERSION.SDK_INT >= 11) {
    if (nodeInfo.isRotationYSet()) {
      view.setRotationY(nodeInfo.getRotationY());
    }
  }
}
