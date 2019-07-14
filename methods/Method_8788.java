private void registerUndo(RectF rect){
  if (rect == null) {
    return;
  }
  boolean intersect=rect.setIntersect(rect,getBounds());
  if (!intersect) {
    return;
  }
  PaintingData paintingData=getPaintingData(rect,true);
  ByteBuffer data=paintingData.data;
  final Slice slice=new Slice(data,rect,delegate.requestDispatchQueue());
  delegate.requestUndoStore().registerUndo(UUID.randomUUID(),new Runnable(){
    @Override public void run(){
      restoreSlice(slice);
    }
  }
);
}
