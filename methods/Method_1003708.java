private void emit() throws Exception {
  boolean any=false;
  Event<Void> event=new DefaultEvent<>(null);
  String id=str(eventId);
  if (id != null) {
    any=true;
    event.id(id);
  }
  String type=str(eventType);
  if (type != null) {
    any=true;
    event.event(type);
  }
  String data=str(eventData);
  if (data != null) {
    any=true;
    event.data(data);
  }
  if (any) {
    emitter.execute(event);
  }
  state=State.ReadFieldName;
}
