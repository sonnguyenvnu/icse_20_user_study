/** 
 * Build a table representation from a <var>tbl</var> instance. Remember to set wordMLPackage before using this method!
 */
public void build(AbstractWmlConversionContext conversionContext,Object node,Node content) throws TransformerException {
  Tbl tbl=null;
  try {
    tbl=(Tbl)node;
  }
 catch (  ClassCastException e) {
    throw new TransformerException("Node is not of the type Tbl it is " + node.getClass().getName());
  }
  if (tbl.getTblPr() != null && tbl.getTblPr().getTblStyle() != null) {
    styleId=tbl.getTblPr().getTblStyle().getVal();
  }
  this.tblGrid=tbl.getTblGrid();
  this.tblPr=tbl.getTblPr();
  PropertyResolver pr=conversionContext.getPropertyResolver();
  effectiveTableStyle=pr.getEffectiveTableStyle(tbl.getTblPr());
  NodeList cellContents=content.getChildNodes();
  TrFinder trFinder=new TrFinder();
  new TraversalUtil(tbl,trFinder);
  ensureFoTableBody(trFinder.getTrList());
  int r=0;
  for (  Tr tr : trFinder.getTrList()) {
    startRow(tr);
    handleRow(cellContents,tr,r);
    r++;
    if (rows.get(row).getRowContents().isEmpty()) {
      rows.remove(row);
      row--;
      r--;
    }
  }
  CTTblPrBase tblPr=effectiveTableStyle.getTblPr();
  if (tblPr != null) {
    if (tblPr.getTblCellSpacing() != null) {
      setBorderConflictResolutionRequired(false);
    }
  }
  width=calcTableWidth();
}
