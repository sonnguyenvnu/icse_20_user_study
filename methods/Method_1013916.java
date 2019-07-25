@Override public boolean apply(Event event){
  logger.trace("->FILTER: {}:{}",event.getTopic(),source);
  return event.getTopic().contains(source);
}
