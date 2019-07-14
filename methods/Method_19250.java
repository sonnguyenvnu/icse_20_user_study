public static int getTopDecorationHeight(RecyclerView.LayoutManager layoutManager,int position){
  final View itemView=layoutManager.getChildAt(position);
  return (itemView != null) ? layoutManager.getTopDecorationHeight(itemView) : 0;
}
