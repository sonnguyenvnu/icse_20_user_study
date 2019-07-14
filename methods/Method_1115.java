/** 
 * Starts fading in the specified layer.
 * @param index the index of the layer to fade in.
 */
public void fadeInLayer(int index){
  mTransitionState=TRANSITION_STARTING;
  mIsLayerOn[index]=true;
  invalidateSelf();
}
