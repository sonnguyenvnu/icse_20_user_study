/** 
 * Starts fading to the specified layer.
 * @param index the index of the layer to fade to
 */
public void fadeToLayer(int index){
  mTransitionState=TRANSITION_STARTING;
  Arrays.fill(mIsLayerOn,false);
  mIsLayerOn[index]=true;
  invalidateSelf();
}
