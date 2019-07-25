public static CTTextScale apply(CTTextScale source,CTTextScale destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createCTTextScale();
    destination.setVal(source.getVal());
  }
  return destination;
}
