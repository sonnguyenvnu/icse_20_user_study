int getFirstChildPosition(){
  final int childCount=getChildCount();
  return childCount == 0 ? 0 : getPosition(getChildAt(0));
}
