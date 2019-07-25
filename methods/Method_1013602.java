public synchronized Enumeration elements(){
  return new SetEnumerator(set);
}
