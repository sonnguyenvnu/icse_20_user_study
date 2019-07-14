public ArrayList<String> getSelectedSeat(){
  ArrayList<String> results=new ArrayList<>();
  for (int i=0; i < this.row; i++) {
    for (int j=0; j < this.column; j++) {
      if (isHave(getID(i,j)) >= 0) {
        results.add(i + "," + j);
      }
    }
  }
  return results;
}
