private Integer getImageMaxHeight(){
  if (imageMaxHeight == null) {
    if (isLandScape) {
      imageMaxHeight=((View)preview.getParent()).getWidth();
    }
 else {
      imageMaxHeight=((View)preview.getParent()).getHeight() - findViewById(R.id.controlPanel).getHeight();
    }
  }
  return imageMaxHeight;
}
