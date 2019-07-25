private UGraphic handwritten(UGraphic ug){
  if (useHandwritten) {
    return new UGraphicHandwritten(ug);
  }
  if (OptionFlags.OMEGA_CROSSING) {
    return new UGraphicCrossing(ug);
  }
 else {
    return ug;
  }
}
