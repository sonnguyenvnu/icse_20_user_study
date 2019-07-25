public UGraphic apply(UChange change){
  if (change instanceof UTranslate) {
    return new CollisionDetector(stringBounder,translate.compose((UTranslate)change),this.context);
  }
 else   if (change instanceof UStroke) {
    return new CollisionDetector(this);
  }
 else   if (change instanceof UChangeBackColor) {
    return new CollisionDetector(this);
  }
 else   if (change instanceof UChangeColor) {
    return new CollisionDetector(this);
  }
  throw new UnsupportedOperationException();
}
