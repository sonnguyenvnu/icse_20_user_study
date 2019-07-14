private static void unsetViewPadding(View view,ViewNodeInfo viewNodeInfo){
  if (!viewNodeInfo.hasPadding()) {
    return;
  }
  view.setPadding(0,0,0,0);
}
