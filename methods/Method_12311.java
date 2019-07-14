/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(java.io.FileDescriptor)}
 * @param fileDescriptor data source
 * @return this builder instance, to chain calls
 */
public T from(FileDescriptor fileDescriptor){
  mInputSource=new InputSource.FileDescriptorSource(fileDescriptor);
  return self();
}
