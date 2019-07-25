@Override public boolean apply(Event event){
  logger.trace("->FILTER: {}:{}",event.getTopic(),itemName);
  return event.getTopic().contains("smarthome/items/" + itemName + "/");
}
