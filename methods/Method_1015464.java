@Override public synchronized void destroy(){
  if (--inits == 0) {
    super.destroy();
    this.protocols.clear();
    FORK fork=findProtocol(FORK.class);
    fork.remove(this.fork_stack_id);
  }
}
