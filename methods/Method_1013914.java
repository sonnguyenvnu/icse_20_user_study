@Override public boolean apply(Event event){
  logger.trace("->FILTER: {}:{}",event.getTopic(),TOPIC);
  boolean eventMatches=false;
  if (event instanceof ChannelTriggeredEvent) {
    ChannelTriggeredEvent cte=(ChannelTriggeredEvent)event;
    if (cte.getTopic().contains(this.channelUID)) {
      logger.trace("->FILTER: {}:{}",cte.getEvent(),eventOnChannel);
      eventMatches=true;
      if (eventOnChannel != null && !eventOnChannel.isEmpty() && !eventOnChannel.equals(cte.getEvent())) {
        eventMatches=false;
      }
    }
  }
  return eventMatches;
}
