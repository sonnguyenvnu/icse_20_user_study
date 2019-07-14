/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(android.content.res.AssetFileDescriptor)}
 * @param assetFileDescriptor data source
 * @return this builder instance, to chain calls
 */
public T from(AssetFileDescriptor assetFileDescriptor){
  mInputSource=new InputSource.AssetFileDescriptorSource(assetFileDescriptor);
  return self();
}
