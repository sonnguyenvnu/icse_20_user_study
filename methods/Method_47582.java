public int getFirstPositionOffset(){
  final View firstChild=getChildAt(0);
  if (firstChild == null) {
    return 0;
  }
  return firstChild.getTop();
}
