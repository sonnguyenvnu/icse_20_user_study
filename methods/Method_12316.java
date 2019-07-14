/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(android.content.res.Resources,int)}
 * @param resources  Resources to read from
 * @param resourceId resource id (data source)
 * @return this builder instance, to chain calls
 */
public T from(Resources resources,int resourceId){
  mInputSource=new InputSource.ResourcesSource(resources,resourceId);
  return self();
}
