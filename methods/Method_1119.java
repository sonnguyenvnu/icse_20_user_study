/** 
 * Makes the specified layer fully opaque
 * @param index the index of the layer to be shown
 */
public void showLayerImmediately(int index){
  mIsLayerOn[index]=true;
  mAlphas[index]=255;
  invalidateSelf();
}
