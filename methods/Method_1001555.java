public void add(Atom atom){
  final Dimension2D dim=atom.calculateDimension(stringBounder);
  final double y=0;
  final Position position=new Position(currentX,y,dim);
  positions.put(atom,position);
  currentX+=dim.getWidth();
}
