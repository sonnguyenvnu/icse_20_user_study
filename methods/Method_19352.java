private static @Nullable RecyclerView.ViewHolder getViewHolderFromLayoutParam(RecyclerView.LayoutParams layoutParams){
  try {
    if (mViewHolderField == null) {
      mViewHolderField=RecyclerView.LayoutParams.class.getDeclaredField("mViewHolder");
      mViewHolderField.setAccessible(true);
    }
    final RecyclerView.ViewHolder viewHolder=(RecyclerView.ViewHolder)mViewHolderField.get(layoutParams);
    return viewHolder;
  }
 catch (  Exception ignore) {
  }
  return null;
}
