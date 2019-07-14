/** 
 * Immediately calls the given callback with the sizes given in the constructor.
 * @param cb {@inheritDoc}
 */
@Override public final void getSize(@NonNull SizeReadyCallback cb){
  if (!Util.isValidDimensions(width,height)) {
    throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given" + " width: " + width + " and height: " + height + ", either provide dimensions in the constructor" + " or call override()");
  }
  cb.onSizeReady(width,height);
}
