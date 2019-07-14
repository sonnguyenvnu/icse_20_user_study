@OnEvent(RenderEvent.class) protected static RenderInfo onRenderEvent(SectionContext c,@FromEvent int index,@FromEvent Object model,@FromEvent Bundle loggingExtras,@Prop EventHandler<RenderWithHideItemHandlerEvent> renderWithHideItemHandler){
  return HideableDataDiffSection.dispatchRenderWithHideItemHandlerEvent(renderWithHideItemHandler,index,model,HideableDataDiffSection.onHideItem(c),loggingExtras);
}
