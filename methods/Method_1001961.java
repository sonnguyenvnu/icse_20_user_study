public TextGrid.Cell find(TextGrid.Cell cell){
  Iterator<TextGrid.Cell> it=iterator();
  while (it.hasNext()) {
    TextGrid.Cell cCell=it.next();
    if (cCell.equals(cell))     return cCell;
  }
  return null;
}
