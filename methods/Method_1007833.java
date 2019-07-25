@Override protected Set<BlockType> load(){
  return WorldEdit.getInstance().getPlatformManager().queryCapability(Capability.GAME_HOOKS).getRegistries().getBlockCategoryRegistry().getAll(this);
}
