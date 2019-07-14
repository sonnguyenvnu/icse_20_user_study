@Override public void uncollect(){
  if (!mResource.hasItem()) {
    return;
  }
  Game game=mResource.getItem();
  UncollectItemManager.getInstance().write(game.getType(),game.id,getActivity());
}
