public Object down(Event evt){
  if (!bypass) {
switch (evt.getType()) {
case Event.CONNECT:
case Event.CONNECT_USE_FLUSH:
      return handleConnect(evt,true);
case Event.CONNECT_WITH_STATE_TRANSFER:
case Event.CONNECT_WITH_STATE_TRANSFER_USE_FLUSH:
    return handleConnect(evt,false);
case Event.SUSPEND:
  startFlush(evt);
return null;
case Event.SUSPEND_BUT_FAIL:
if (!flushInProgress.get()) {
flush_promise.reset();
ArrayList<Address> flushParticipants=null;
synchronized (sharedLock) {
  flushParticipants=new ArrayList<>(currentView.getMembers());
}
onSuspend(flushParticipants);
}
break;
case Event.RESUME:
onResume(evt);
return null;
case Event.SET_LOCAL_ADDRESS:
localAddress=evt.getArg();
break;
}
}
return down_prot.down(evt);
}
