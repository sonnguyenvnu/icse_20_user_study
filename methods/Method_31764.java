private void resetDefaults(TableView tView){
  Object[] colNames=tView.getColNames();
  Object[][] tableValues=tView.getCalcdValues();
  JTable table=new JTable(tableValues,colNames);
  tView.setViewColumnsWidth(table);
  setTitle(tView.getViewTitle());
  if (mainSP != null)   getContentPane().remove(mainSP);
  mainSP=new JScrollPane(table);
  getContentPane().add(mainSP,"Center");
  validate();
}
