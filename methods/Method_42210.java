public static RecyclerView.ItemAnimator getItemAnimator(RecyclerView.ItemAnimator itemAnimator){
  if (Prefs.animationsEnabled()) {
    return itemAnimator;
  }
  return null;
}
