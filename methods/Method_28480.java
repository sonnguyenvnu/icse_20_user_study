public void initArguments(@NonNull String title,@NonNull List<O> objects){
  setArguments(Bundler.start().put(BundleConstant.EXTRA,title).putParcelableArrayList(BundleConstant.ITEM,(ArrayList<? extends Parcelable>)objects).end());
}
