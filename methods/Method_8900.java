private void duplicateSelectedEntity(){
  if (currentEntityView == null)   return;
  EntityView entityView=null;
  Point position=startPositionRelativeToEntity(currentEntityView);
  if (currentEntityView instanceof StickerView) {
    StickerView newStickerView=new StickerView(getContext(),(StickerView)currentEntityView,position);
    newStickerView.setDelegate(this);
    entitiesView.addView(newStickerView);
    entityView=newStickerView;
  }
 else   if (currentEntityView instanceof TextPaintView) {
    TextPaintView newTextPaintView=new TextPaintView(getContext(),(TextPaintView)currentEntityView,position);
    newTextPaintView.setDelegate(this);
    newTextPaintView.setMaxWidth((int)(getPaintingSize().width - 20));
    entitiesView.addView(newTextPaintView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT));
    entityView=newTextPaintView;
  }
  registerRemovalUndo(entityView);
  selectEntity(entityView);
  updateSettingsButton();
}
