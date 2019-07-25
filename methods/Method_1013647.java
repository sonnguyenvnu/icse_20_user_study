public void open(){
  if (isOpen.compareAndSet(false,true)) {
    logger.info("[open][{}]",name);
    makeAllPass();
  }
}
