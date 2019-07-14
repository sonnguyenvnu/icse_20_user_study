int getItemCount(){
  assertMainThread();
  return mLayoutOutputsIds == null ? 0 : mLayoutOutputsIds.length;
}
