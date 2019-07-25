public static TblBorders apply(TblBorders source,TblBorders destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createTblBorders();
    destination.setBottom(apply(source.getBottom(),destination.getBottom()));
    destination.setLeft(apply(source.getLeft(),destination.getLeft()));
    destination.setRight(apply(source.getRight(),destination.getRight()));
    destination.setTop(apply(source.getTop(),destination.getTop()));
    destination.setInsideH(apply(source.getInsideH(),destination.getInsideH()));
    destination.setInsideV(apply(source.getInsideV(),destination.getInsideV()));
  }
  return destination;
}
