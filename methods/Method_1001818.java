public UGraphic apply(UChange change){
  return new UGraphicInterceptorTile(getUg().apply(change),isBackground);
}
