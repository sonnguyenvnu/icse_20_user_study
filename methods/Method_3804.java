private List<ViewHolder> findSwapTargets(ViewHolder viewHolder){
  if (mSwapTargets == null) {
    mSwapTargets=new ArrayList<>();
    mDistances=new ArrayList<>();
  }
 else {
    mSwapTargets.clear();
    mDistances.clear();
  }
  final int margin=mCallback.getBoundingBoxMargin();
  final int left=Math.round(mSelectedStartX + mDx) - margin;
  final int top=Math.round(mSelectedStartY + mDy) - margin;
  final int right=left + viewHolder.itemView.getWidth() + 2 * margin;
  final int bottom=top + viewHolder.itemView.getHeight() + 2 * margin;
  final int centerX=(left + right) / 2;
  final int centerY=(top + bottom) / 2;
  final RecyclerView.LayoutManager lm=mRecyclerView.getLayoutManager();
  final int childCount=lm.getChildCount();
  for (int i=0; i < childCount; i++) {
    View other=lm.getChildAt(i);
    if (other == viewHolder.itemView) {
      continue;
    }
    if (other.getBottom() < top || other.getTop() > bottom || other.getRight() < left || other.getLeft() > right) {
      continue;
    }
    final ViewHolder otherVh=mRecyclerView.getChildViewHolder(other);
    if (mCallback.canDropOver(mRecyclerView,mSelected,otherVh)) {
      final int dx=Math.abs(centerX - (other.getLeft() + other.getRight()) / 2);
      final int dy=Math.abs(centerY - (other.getTop() + other.getBottom()) / 2);
      final int dist=dx * dx + dy * dy;
      int pos=0;
      final int cnt=mSwapTargets.size();
      for (int j=0; j < cnt; j++) {
        if (dist > mDistances.get(j)) {
          pos++;
        }
 else {
          break;
        }
      }
      mSwapTargets.add(pos,otherVh);
      mDistances.add(pos,dist);
    }
  }
  return mSwapTargets;
}
