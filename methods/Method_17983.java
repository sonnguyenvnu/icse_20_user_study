/** 
 * Takes ownership of the  {@link RenderState} object from this ComponentTree - this allows theRenderState to be persisted somewhere and then set back on another ComponentTree using the {@link Builder}. See  {@link RenderState} for more information on the purpose of this object.
 */
@ThreadConfined(ThreadConfined.UI) public RenderState consumePreviousRenderState(){
  final RenderState previousRenderState=mPreviousRenderState;
  mPreviousRenderState=null;
  mPreviousRenderStateSetFromBuilder=false;
  return previousRenderState;
}
