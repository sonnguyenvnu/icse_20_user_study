/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(java.io.File)}
 * @param file data source
 * @return this builder instance, to chain calls
 */
public T from(File file){
  mInputSource=new InputSource.FileSource(file);
  return self();
}
