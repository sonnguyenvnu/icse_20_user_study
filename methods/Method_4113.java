private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder vh,int flag){
  int index=mLayoutHolderMap.indexOfKey(vh);
  if (index < 0) {
    return null;
  }
  final InfoRecord record=mLayoutHolderMap.valueAt(index);
  if (record != null && (record.flags & flag) != 0) {
    record.flags&=~flag;
    final RecyclerView.ItemAnimator.ItemHolderInfo info;
    if (flag == FLAG_PRE) {
      info=record.preInfo;
    }
 else     if (flag == FLAG_POST) {
      info=record.postInfo;
    }
 else {
      throw new IllegalArgumentException("Must provide flag PRE or POST");
    }
    if ((record.flags & (FLAG_PRE | FLAG_POST)) == 0) {
      mLayoutHolderMap.removeAt(index);
      InfoRecord.recycle(record);
    }
    return info;
  }
  return null;
}
