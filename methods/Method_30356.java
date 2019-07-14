@Override public void uncollect(){
  if (!mResource.hasItem()) {
    return;
  }
  Movie movie=mResource.getItem();
  UncollectItemManager.getInstance().write(movie.getType(),movie.id,getActivity());
}
