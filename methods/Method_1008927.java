public static TblStyleRowBandSize apply(TblStyleRowBandSize source,TblStyleRowBandSize destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createCTTblPrBaseTblStyleRowBandSize();
    destination.setVal(source.getVal());
  }
  return destination;
}
