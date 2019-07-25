public static CTVerticalAlignRun apply(CTVerticalAlignRun source,CTVerticalAlignRun destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createCTVerticalAlignRun();
    destination.setVal(source.getVal());
  }
  return destination;
}
