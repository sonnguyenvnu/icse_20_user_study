public static CTEm apply(CTEm source,CTEm destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createCTEm();
    destination.setVal(source.getVal());
  }
  return destination;
}
