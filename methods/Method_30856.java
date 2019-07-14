protected void init(@NonNull Class<T> classOfT,@NonNull SortedList.Callback<T> callback){
  mSortedList=new SortedList<>(classOfT,callback);
}
