public Positionned translate(UTranslate other){
  return new PositionnedImpl(cuteShape,color,position.compose(other),rotationZoom);
}
