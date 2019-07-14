public void setCell(int cellIndex,Cell cell){
  if (cellIndex < cells.size()) {
    cells.set(cellIndex,cell);
  }
 else {
    while (cellIndex > cells.size()) {
      cells.add(null);
    }
    cells.add(cell);
  }
}
