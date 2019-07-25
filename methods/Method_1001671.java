public UGraphic apply(UChange change){
  return new UGraphicAddAriaLabel(getUg().apply(change),ariaLabel);
}
