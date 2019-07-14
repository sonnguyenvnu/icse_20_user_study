private void createSticker(Object parentObject,TLRPC.Document sticker){
  StickerPosition position=calculateStickerPosition(sticker);
  StickerView view=new StickerView(getContext(),position.position,position.angle,position.scale,baseStickerSize(),sticker,parentObject);
  view.setDelegate(this);
  entitiesView.addView(view);
  registerRemovalUndo(view);
  selectEntity(view);
}
