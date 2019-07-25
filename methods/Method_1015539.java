protected void process(Collection<Request> requests){
  if (requests.isEmpty())   return;
  Request firstReq=requests.iterator().next();
switch (firstReq.type) {
case Request.JOIN:
case Request.JOIN_WITH_STATE_TRANSFER:
case Request.LEAVE:
case Request.SUSPECT:
    impl.handleMembershipChange(requests);
  break;
case Request.COORD_LEAVE:
impl.handleCoordLeave(firstReq.mbr);
break;
case Request.MERGE:
impl.merge(firstReq.views);
break;
default :
log.error("request type " + firstReq.type + " is unknown; discarded");
}
}
