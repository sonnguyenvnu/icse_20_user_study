public UGraphic apply(UChange change){
  if (change instanceof UTranslate) {
    return new SlotFinder(mode,stringBounder,slot,translate.compose((UTranslate)change));
  }
 else   if (change instanceof UStroke) {
    return new SlotFinder(this);
  }
 else   if (change instanceof UChangeBackColor) {
    return new SlotFinder(this);
  }
 else   if (change instanceof UChangeColor) {
    return new SlotFinder(this);
  }
  throw new UnsupportedOperationException();
}
