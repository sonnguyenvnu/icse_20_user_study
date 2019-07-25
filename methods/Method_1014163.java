@Override public String interpret(Locale locale,String text) throws InterpretationException {
  Event event=ItemEventFactory.createCommandEvent(itemName,new StringType(text));
  eventPublisher.post(event);
  return null;
}
