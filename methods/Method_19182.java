/** 
 * @return Valid {@link EventHandler<RenderCompleteEvent>} if {@link RenderInfo} was createdthrough  {@link ComponentRenderInfo#create()}, otherwise it will throw  {@link UnsupportedOperationException}.
 */
@Override @Nullable public EventHandler<RenderCompleteEvent> getRenderCompleteEventHandler(){
  throw new UnsupportedOperationException();
}
