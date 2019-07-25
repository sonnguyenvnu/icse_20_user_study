public void build(Tbl tbl){
  this.tbl=tbl;
  if (tbl.getTblPr() != null && tbl.getTblPr().getTblStyle() != null) {
    styleId=tbl.getTblPr().getTblStyle().getVal();
  }
  this.tblGrid=tbl.getTblGrid();
  this.tblPr=tbl.getTblPr();
  TrFinder trFinder=new TrFinder();
  new TraversalUtil(tbl,trFinder);
  int r=0;
  for (  Tr tr : trFinder.getTrList()) {
    startRow(tr);
    handleRow(tr,r);
    r++;
    if (log.isDebugEnabled()) {
      log.debug("\n\n" + this.debugStr());
    }
    if (rows.get(row).getRowContents().isEmpty()) {
      log.debug("removing empty row");
      rows.remove(row);
      row--;
      r--;
    }
  }
  width=calcTableWidth();
}
