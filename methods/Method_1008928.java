public static GridSpan apply(GridSpan source,GridSpan destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createTcPrInnerGridSpan();
    destination.setVal(source.getVal());
  }
  return destination;
}
