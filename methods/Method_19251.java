public static int getBottomDecorationHeight(RecyclerView.LayoutManager layoutManager,int position){
  final View itemView=layoutManager.getChildAt(position);
  return (itemView != null) ? layoutManager.getBottomDecorationHeight(itemView) : 0;
}
