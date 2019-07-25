public static CTShortHexNumber apply(CTShortHexNumber source,CTShortHexNumber destination){
  if ((source != null) && (!isEmpty(source.getVal()))) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createCTShortHexNumber();
    destination.setVal(source.getVal());
  }
  return destination;
}
