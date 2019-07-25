public final synchronized void bind(Graphics graphics) throws IllegalStateException, NullPointerException {
  if (mIsBound) {
    throw new IllegalStateException("Target already bound");
  }
  this.mGraphics=graphics;
  mIsBound=true;
}
