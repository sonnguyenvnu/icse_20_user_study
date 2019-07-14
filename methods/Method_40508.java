public boolean isRef(){
  return !(isCall() || isNew());
}
