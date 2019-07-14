public final void setHost(@NonNull LifecycleHandler lifecycleHandler,@NonNull ViewGroup container){
  if (this.lifecycleHandler != lifecycleHandler || this.container != container) {
    if (this.container != null && this.container instanceof ControllerChangeListener) {
      removeChangeListener((ControllerChangeListener)this.container);
    }
    if (container instanceof ControllerChangeListener) {
      addChangeListener((ControllerChangeListener)container);
    }
    this.lifecycleHandler=lifecycleHandler;
    this.container=container;
    watchContainerAttach();
  }
}
