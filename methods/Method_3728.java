@SuppressWarnings("WeakerAccess") void latchList(@NonNull List<T> newList,@NonNull DiffUtil.DiffResult diffResult,@Nullable Runnable commitCallback){
  final List<T> previousList=mReadOnlyList;
  mList=newList;
  mReadOnlyList=Collections.unmodifiableList(newList);
  diffResult.dispatchUpdatesTo(mUpdateCallback);
  onCurrentListChanged(previousList,commitCallback);
}
