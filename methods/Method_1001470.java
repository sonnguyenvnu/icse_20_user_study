public UGraphic apply(UChange change){
  if (change instanceof UTranslate) {
    return new ZadBuilder(stringBounder,translate.compose((UTranslate)change),this.context);
  }
 else   if (change instanceof UStroke) {
    return new ZadBuilder(this);
  }
 else   if (change instanceof UChangeBackColor) {
    return new ZadBuilder(this);
  }
 else   if (change instanceof UChangeColor) {
    return new ZadBuilder(this);
  }
  throw new UnsupportedOperationException();
}
