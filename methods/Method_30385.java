@Override public void uncollect(){
  if (!mResource.hasItem()) {
    return;
  }
  Music music=mResource.getItem();
  UncollectItemManager.getInstance().write(music.getType(),music.id,getActivity());
}
