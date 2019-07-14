/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(android.content.ContentResolver,android.net.Uri)}
 * @param uri             data source
 * @param contentResolver resolver used to query {@code uri}
 * @return this builder instance, to chain calls
 */
public T from(ContentResolver contentResolver,Uri uri){
  mInputSource=new InputSource.UriSource(contentResolver,uri);
  return self();
}
