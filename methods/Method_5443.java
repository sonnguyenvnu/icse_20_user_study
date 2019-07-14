@Override @Nullable public Object getTag(){
  return mediaSources.length > 0 ? mediaSources[0].getTag() : null;
}
