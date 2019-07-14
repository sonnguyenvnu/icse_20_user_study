/** 
 * Starts fading out the specified layer.
 * @param index the index of the layer to fade out.
 */
public void fadeOutLayer(int index){
  mTransitionState=TRANSITION_STARTING;
  mIsLayerOn[index]=false;
  invalidateSelf();
}
