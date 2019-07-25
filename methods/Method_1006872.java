public void remove(){
  if (contextHolder.get() != null) {
    contextHolder.remove();
  }
}
