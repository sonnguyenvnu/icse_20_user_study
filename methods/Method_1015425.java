/** 
 * <b>Callback</b>. <p> Called by the protocol below when a message has been received. The algorithm should test whether the message is destined for us and, if not, pass it up to the next layer. Otherwise, it should remove the header and check whether the message is a request or response. In the first case, the message will be delivered to the request handler registered (calling its  {@code handle()} method), in the second case, the corresponding response collector is looked up andthe message delivered.
 * @param evt The event to be received
 * @return Whether or not the event was consumed. If true, don't pass message up, else pass it up
 */
public boolean receive(Event evt){
switch (evt.getType()) {
case Event.VIEW_CHANGE:
    receiveView(evt.getArg());
  break;
case Event.SET_LOCAL_ADDRESS:
setLocalAddress(evt.getArg());
break;
case Event.SITE_UNREACHABLE:
SiteMaster site_master=evt.getArg();
String site=site_master.getSite();
setSiteUnreachable(site);
break;
}
return false;
}
