public String activate(Participant p,LifeEventType lifeEventType,HtmlColor backcolor,HtmlColor linecolor){
  if (lastDelay != null) {
    return "You cannot Activate/Deactivate just after a ...";
  }
  final LifeEvent lifeEvent=new LifeEvent(p,lifeEventType,new SymbolContext(backcolor,linecolor));
  events.add(lifeEvent);
  if (lifeEventType == LifeEventType.CREATE) {
    pendingCreate=lifeEvent;
    return null;
  }
  if (lastEventWithDeactivate == null) {
    if (lifeEventType == LifeEventType.ACTIVATE) {
      p.incInitialLife(new SymbolContext(backcolor,linecolor));
      return null;
    }
    if (p.getInitialLife() == 0) {
      return "You cannot deactivate here";
    }
    return null;
  }
  if (lifeEventType == LifeEventType.ACTIVATE && lastEventWithDeactivate instanceof Message) {
    activationState.push((Message)lastEventWithDeactivate);
  }
 else   if (lifeEventType == LifeEventType.DEACTIVATE && activationState.empty() == false) {
    activationState.pop();
  }
  final boolean ok=lastEventWithDeactivate.addLifeEvent(lifeEvent);
  if (lastEventWithDeactivate instanceof AbstractMessage) {
    lifeEvent.setMessage((AbstractMessage)lastEventWithDeactivate);
  }
  if (ok) {
    return null;
  }
  return "Activate/Deactivate already done on " + p.getCode();
}
