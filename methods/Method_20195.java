@NonNull @Override List<? extends EpoxyModel<?>> getCurrentModels(){
  return differ.getCurrentList();
}
