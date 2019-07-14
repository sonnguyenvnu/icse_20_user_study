/** 
 * Appropriate constructor wrapper. Must be preceded by on of  {@code from()} calls.
 * @return new drawable instance
 * @throws IOException when creation fails
 */
public GifDrawable build() throws IOException {
  if (mInputSource == null) {
    throw new NullPointerException("Source is not set");
  }
  return mInputSource.build(mOldDrawable,mExecutor,mIsRenderingTriggeredOnDraw,mOptions);
}
