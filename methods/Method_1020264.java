public final synchronized void release(Graphics graphics) throws IllegalArgumentException, NullPointerException {
  if (graphics != this.mGraphics) {
    throw new IllegalArgumentException("Unknown target");
  }
 else   if (graphics == this.mGraphics && mIsBound) {
    this.mGraphics=null;
    mIsBound=false;
  }
}
