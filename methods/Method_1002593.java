public void reset(HttpRequest request){
  this.request=request;
  serialTask=null;
  unsafe.putOrderedInt(this,closedRanOffset,0);
  unsafe.putOrderedInt(this,headerSentOffset,0);
  unsafe.putOrderedObject(this,closeHandlerOffset,null);
  unsafe.putOrderedObject(this,receiveHandlerOffset,null);
  unsafe.putOrderedObject(this,pingHandlerOffset,null);
}
