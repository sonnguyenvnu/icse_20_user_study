private boolean getStackFromEnd(){
  final LayoutManager layoutManager=mLayoutInfo.getLayoutManager();
  if (layoutManager instanceof LinearLayoutManager) {
    return ((LinearLayoutManager)layoutManager).getStackFromEnd();
  }
 else {
    return false;
  }
}
