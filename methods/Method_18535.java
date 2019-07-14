@Nullable @Override public String getTransitionKey(){
  return mObjectProps != null ? (String)mObjectProps.get(INDEX_TransitionKey) : null;
}
