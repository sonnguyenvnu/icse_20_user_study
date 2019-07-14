protected boolean shouldPersist(){
  return getSharedPreferences() != null && isPersistent() && hasKey();
}
