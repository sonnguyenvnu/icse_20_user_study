public static CTTblPrBase apply(CTTblPrBase source,CTTblPrBase destination){
  if (!isEmpty(source)) {
    if (destination == null)     destination=Context.getWmlObjectFactory().createTblPr();
    destination.setBidiVisual(apply(source.getBidiVisual(),destination.getBidiVisual()));
    destination.setTblStyle(apply(source.getTblStyle(),destination.getTblStyle()));
    destination.setTblpPr(apply(source.getTblpPr(),destination.getTblpPr()));
    destination.setTblOverlap(apply(source.getTblOverlap(),destination.getTblOverlap()));
    destination.setTblStyleRowBandSize(apply(source.getTblStyleRowBandSize(),destination.getTblStyleRowBandSize()));
    destination.setTblStyleColBandSize(apply(source.getTblStyleColBandSize(),destination.getTblStyleColBandSize()));
    destination.setTblW(apply(source.getTblW(),destination.getTblW()));
    destination.setJc(apply(source.getJc(),destination.getJc()));
    destination.setTblCellSpacing(apply(source.getTblCellSpacing(),destination.getTblCellSpacing()));
    destination.setTblInd(apply(source.getTblInd(),destination.getTblInd()));
    destination.setTblBorders(apply(source.getTblBorders(),destination.getTblBorders()));
    destination.setShd(apply(source.getShd(),destination.getShd()));
    destination.setTblLayout(apply(source.getTblLayout(),destination.getTblLayout()));
    destination.setTblCellMar(apply(source.getTblCellMar(),destination.getTblCellMar()));
    destination.setTblLook(apply(source.getTblLook(),destination.getTblLook()));
  }
  return destination;
}
