@Override protected Set<ItemType> load(){
  return WorldEdit.getInstance().getPlatformManager().queryCapability(Capability.GAME_HOOKS).getRegistries().getItemCategoryRegistry().getAll(this);
}
