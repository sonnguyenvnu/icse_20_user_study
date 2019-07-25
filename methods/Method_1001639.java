public void normalize(){
  int minRow=Integer.MAX_VALUE;
  int minCol=Integer.MAX_VALUE;
  int maxRow=Integer.MIN_VALUE;
  int maxCol=Integer.MIN_VALUE;
  for (  Map.Entry<ANode,Integer> ent : nodesCols.entrySet()) {
    minRow=Math.min(minRow,ent.getKey().getRow());
    maxRow=Math.max(maxRow,ent.getKey().getRow());
    minCol=Math.min(minCol,ent.getValue());
    maxCol=Math.max(maxCol,ent.getValue());
  }
  for (  Map.Entry<ANode,Integer> ent : nodesCols.entrySet()) {
    if (minRow != 0) {
      ent.getKey().setRow(ent.getKey().getRow() - minRow);
    }
    if (minCol != 0) {
      ent.setValue(ent.getValue() - minCol);
    }
  }
}
