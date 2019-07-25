private boolean exe(){
  if (!execute || !allowGenerateRoot) {
    execute=false;
    return false;
  }
  return true;
}
