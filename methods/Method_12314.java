/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(java.lang.String)}
 * @param filePath data source
 * @return this builder instance, to chain calls
 */
public T from(String filePath){
  mInputSource=new InputSource.FileSource(filePath);
  return self();
}
