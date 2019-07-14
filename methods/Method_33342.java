private boolean isHorizontal(){
  final Side tabPosition=getSkinnable().getSide();
  return Side.TOP.equals(tabPosition) || Side.BOTTOM.equals(tabPosition);
}
