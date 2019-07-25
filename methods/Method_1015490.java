public void unlocked(String lock_name,Owner owner){
  if (is_coord)   updateBackups(Type.DELETE_LOCK,lock_name,owner);
}
