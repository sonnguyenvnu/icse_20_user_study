@Override public @Nullable Object getViewTag(){
  return (mPrivateFlags & PFLAG_VIEW_TAG_IS_SET) != 0 ? getOrCreateObjectProps().get(INDEX_ViewTag) : null;
}
