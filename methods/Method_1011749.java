@Override public void apply(@NotNull SModel model,@NotNull NodeCopier nodeCopier){
  assert model == getChangeSet().getOldModel();
  super.apply(model,nodeCopier);
}
