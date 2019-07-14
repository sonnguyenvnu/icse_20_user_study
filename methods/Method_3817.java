private static float findMaxElevation(RecyclerView recyclerView,View itemView){
  final int childCount=recyclerView.getChildCount();
  float max=0;
  for (int i=0; i < childCount; i++) {
    final View child=recyclerView.getChildAt(i);
    if (child == itemView) {
      continue;
    }
    final float elevation=ViewCompat.getElevation(child);
    if (elevation > max) {
      max=elevation;
    }
  }
  return max;
}
