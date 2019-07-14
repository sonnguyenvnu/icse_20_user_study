/** 
 * Sets drawable to be reused when creating new one.
 * @param drawable drawable to be reused
 * @return this builder instance, to chain calls
 */
public T with(GifDrawable drawable){
  mOldDrawable=drawable;
  return self();
}
