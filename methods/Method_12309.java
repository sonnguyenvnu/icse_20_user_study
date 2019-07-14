/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(java.io.InputStream)}
 * @param inputStream data source
 * @return this builder instance, to chain calls
 */
public T from(InputStream inputStream){
  mInputSource=new InputSource.InputStreamSource(inputStream);
  return self();
}
