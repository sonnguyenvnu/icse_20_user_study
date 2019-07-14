/** 
 * Makes the specified layer fully transparent
 * @param index the index of the layer to be hidden
 */
public void hideLayerImmediately(int index){
  mIsLayerOn[index]=false;
  mAlphas[index]=0;
  invalidateSelf();
}
