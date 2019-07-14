private void setForegroundModeInternal(boolean foregroundMode,@Nullable AtomicBoolean processedFlag){
  if (this.foregroundMode != foregroundMode) {
    this.foregroundMode=foregroundMode;
    if (!foregroundMode) {
      for (      Renderer renderer : renderers) {
        if (renderer.getState() == Renderer.STATE_DISABLED) {
          renderer.reset();
        }
      }
    }
  }
  if (processedFlag != null) {
synchronized (this) {
      processedFlag.set(true);
      notifyAll();
    }
  }
}
