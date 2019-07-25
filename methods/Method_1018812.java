private void init(){
  attacher=new PhotoViewAttacher(this);
  super.setScaleType(ScaleType.MATRIX);
  if (pendingScaleType != null) {
    setScaleType(pendingScaleType);
    pendingScaleType=null;
  }
}
