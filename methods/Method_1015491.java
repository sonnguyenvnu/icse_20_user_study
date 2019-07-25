public void awaited(String lock_name,Owner owner){
  if (is_coord)   updateBackups(Type.DELETE_AWAITER,lock_name,owner);
}
