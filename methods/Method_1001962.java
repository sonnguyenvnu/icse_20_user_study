public TextGrid.Cell find(int x,int y){
  Iterator it=iterator();
  while (it.hasNext()) {
    TextGrid.Cell cCell=(TextGrid.Cell)it.next();
    if (cCell.x == x && cCell.y == y)     return cCell;
  }
  return null;
}
