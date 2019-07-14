@Override protected double computePrefWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  for (  TabContentHolder tabContentHolder : tabContentHolders) {
    maxWidth=Math.max(maxWidth,snapSize(tabContentHolder.prefWidth(-1)));
  }
  final double headerContainerWidth=snapSize(header.prefWidth(-1));
  double prefWidth=Math.max(maxWidth,headerContainerWidth);
  return snapSize(prefWidth) + rightInset + leftInset;
}
