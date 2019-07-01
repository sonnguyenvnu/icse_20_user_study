@Override public List<StreamEvent> _XXXXX_(Tuple tuple) throws Exception {
  long timestamp;
  if (tuple.getFields().contains(TIMESTAMP_FIELD)) {
    try {
      timestamp=tuple.getLongByField("timestamp");
    }
 catch (    Exception ex) {
      LOGGER.error(ex.getMessage(),ex);
      timestamp=0;
    }
  }
 else {
    timestamp=System.currentTimeMillis();
  }
  Object[] values=new Object[tuple.getFields().size()];
  for (int i=0; i < tuple.getFields().size(); i++) {
    values[i]=tuple.getValue(i);
  }
  StreamEvent event=new StreamEvent();
  event.setTimestamp(timestamp);
  event.setStreamId(streamId);
  event.setData(values);
  return Collections.singletonList(event);
}