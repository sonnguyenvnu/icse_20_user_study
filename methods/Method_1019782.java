public void flush(){
  if (flushed) {
    return;
  }
  super.addCell(tableCell);
  flushed=true;
}
