/** 
 * Starts fading up to the specified layer. <p> Layers up to the specified layer inclusive will fade in, other layers will fade out.
 * @param index the index of the layer to fade up to.
 */
public void fadeUpToLayer(int index){
  mTransitionState=TRANSITION_STARTING;
  Arrays.fill(mIsLayerOn,0,index + 1,true);
  Arrays.fill(mIsLayerOn,index + 1,mLayers.length,false);
  invalidateSelf();
}
