public Event parse(String xml) throws Exception {
  if (xml == null || xml.length() == 0) {
    throw new RuntimeException("Null or empty XML");
  }
  Event event=new Event();
  new RootHandler(event,this);
  if (log.isLoggable(Level.FINE)) {
    log.fine("Parsing 'LastChange' event XML content");
    log.fine("===================================== 'LastChange' BEGIN ============================================");
    log.fine(xml);
    log.fine("====================================== 'LastChange' END  ============================================");
  }
  parse(new InputSource(new StringReader(xml)));
  log.fine("Parsed event with instances IDs: " + event.getInstanceIDs().size());
  if (log.isLoggable(Level.FINEST)) {
    for (    InstanceID instanceID : event.getInstanceIDs()) {
      log.finest("InstanceID '" + instanceID.getId() + "' has values: " + instanceID.getValues().size());
      for (      EventedValue eventedValue : instanceID.getValues()) {
        log.finest(eventedValue.getName() + " => " + eventedValue.getValue());
      }
    }
  }
  return event;
}
