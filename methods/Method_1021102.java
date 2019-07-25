private List<ConsumedEvent> extract(final int count){
  final List<ConsumedEvent> result=new ArrayList<>();
  for (int i=0; i < count && !nakadiEvents.isEmpty(); ++i) {
    final ConsumedEvent event=nakadiEvents.remove(0);
    bytesInMemory-=event.getEvent().length;
    result.add(event);
  }
  if (!result.isEmpty()) {
    this.sentOffset=result.get(result.size() - 1).getPosition();
    this.keepAliveInARow=0;
  }
 else {
    this.keepAliveInARow+=1;
  }
  return result;
}
