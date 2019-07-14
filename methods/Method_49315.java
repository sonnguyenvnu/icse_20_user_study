public final void updateLifeCycle(ElementLifeCycle.Event event){
synchronized (lifecycleMutex) {
    this.lifecycle=ElementLifeCycle.update(lifecycle,event);
  }
}
