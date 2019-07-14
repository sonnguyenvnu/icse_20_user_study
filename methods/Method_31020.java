public static boolean hasFirstChildReachedTop(RecyclerView recyclerView,int top){
  RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
  View firstChild=layoutManager.findViewByPosition(0);
  if (firstChild != null) {
    return firstChild.getTop() <= top;
  }
 else {
    return layoutManager.getChildCount() > 0;
  }
}
