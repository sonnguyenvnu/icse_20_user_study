public void rectangle(double x,double y,double width,double height){
  ensureVisible(x,y);
  ensureVisible(x + width,y + height);
  final VisioRectangle rect=VisioRectangle.createInches(shapes.size() + 1,x,y,width,height);
  shapes.add(rect);
}
