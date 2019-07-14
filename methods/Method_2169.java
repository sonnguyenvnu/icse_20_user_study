protected void onTransformChanged(Matrix transform){
  FLog.v(getLogTag(),"onTransformChanged: view %x, transform: %s",this.hashCode(),transform);
  maybeSetHugeImageController();
  invalidate();
}
