public void initArguments(@NonNull String title,@NonNull ArrayList<O> objects){
  setArguments(Bundler.start().put(BundleConstant.EXTRA,title).putParcelableArrayList(BundleConstant.ITEM,objects).end());
}
