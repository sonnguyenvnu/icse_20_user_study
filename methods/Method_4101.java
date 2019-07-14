int getLastChildPosition(){
  final int childCount=getChildCount();
  return childCount == 0 ? 0 : getPosition(getChildAt(childCount - 1));
}
