public void addRow(Problem data,String msg,String filename,String line){
  DefaultTableModel dtm=(DefaultTableModel)getModel();
  dtm.addRow(new Object[]{data,msg,filename,line});
}
