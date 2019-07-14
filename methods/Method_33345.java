@Override protected double computePrefHeight(double width,double topInset,double rightInset,double bottomInset,double leftInset){
  for (  TabContentHolder tabContentHolder : tabContentHolders) {
    maxHeight=Math.max(maxHeight,snapSize(tabContentHolder.prefHeight(-1)));
  }
  final double headerContainerHeight=snapSize(header.prefHeight(-1));
  double prefHeight=maxHeight + snapSize(headerContainerHeight);
  return snapSize(prefHeight) + topInset + bottomInset;
}
