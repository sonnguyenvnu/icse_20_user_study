public void translate(int dx,int dy){
  typeIsValid=false;
  Iterator it=iterator();
  while (it.hasNext()) {
    TextGrid.Cell cCell=(TextGrid.Cell)it.next();
    cCell.x+=dx;
    cCell.y+=dy;
  }
}
