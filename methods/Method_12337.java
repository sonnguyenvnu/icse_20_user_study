/** 
 * Wrapper of  {@link #setTransform(Matrix)}. Introduced to preserve the same API as in {@link GifImageView}.
 * @param matrix The transform to apply to the content of this view.
 */
public void setImageMatrix(Matrix matrix){
  setTransform(matrix);
}
