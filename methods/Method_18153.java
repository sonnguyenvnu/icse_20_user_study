@Override public String getSimpleName(){
  return mComponents.isEmpty() ? "<null>" : mComponents.get(0).getSimpleName();
}
