/** 
 * Applies the given dimensions to the drawable. This method should be called in your component's @OnBind method.
 * @param width The width of the drawable to be drawn.
 * @param height The height of the drawable to be drawn.
 */
public void bind(int width,int height){
  mWidth=width;
  mHeight=height;
  setInnerDrawableBounds(mWidth,mHeight);
}
