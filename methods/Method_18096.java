@Override public @Nullable Component getHeadComponent(){
  return mComponents.isEmpty() ? null : mComponents.get(mComponents.size() - 1);
}
