@Nullable @Override public int[] getPreloadSize(IconDataParcelable item,int adapterPosition,int perItemPosition){
  return viewSizes.get(callback.getCorrectView(item,adapterPosition),null);
}
