private void addChooseSeat(int row,int column){
  int id=getID(row,column);
  for (int i=0; i < selects.size(); i++) {
    int item=selects.get(i);
    if (id < item) {
      selects.add(i,id);
      return;
    }
  }
  selects.add(id);
}
