/** 
 * Returns length of the input source obtained at the opening time or -1 if length cannot be determined. Returned value does not change during runtime. If GifDrawable is constructed from  {@link InputStream} -1 is always returned.In case of byte array and  {@link ByteBuffer} length is always known.In other cases length -1 can be returned if length cannot be determined.
 * @return number of bytes backed by input source or -1 if it is unknown
 */
public long getInputSourceByteCount(){
  return mNativeInfoHandle.getSourceLength();
}
