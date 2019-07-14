@Override public void onAreaChangeEnded(){
  areaView.setGridType(CropAreaView.GridType.NONE,true);
  fillAreaView(areaView.getTargetRectToFill(),false);
}
