public boolean dispatchEvent(Event evt) throws EventException {
  EventImpl eventImpl=(EventImpl)evt;
  if (!eventImpl.isInitialized()) {
    throw new EventException(EventException.UNSPECIFIED_EVENT_TYPE_ERR,"Event not initialized");
  }
 else   if ((eventImpl.getType() == null) || eventImpl.getType().equals("")) {
    throw new EventException(EventException.UNSPECIFIED_EVENT_TYPE_ERR,"Unspecified even type");
  }
  eventImpl.setTarget(mNodeTarget);
  eventImpl.setEventPhase(Event.AT_TARGET);
  eventImpl.setCurrentTarget(mNodeTarget);
  if (!eventImpl.isPropogationStopped() && (mListenerEntries != null)) {
    for (int i=0; i < mListenerEntries.size(); i++) {
      EventListenerEntry listenerEntry=mListenerEntries.get(i);
      if (!listenerEntry.mUseCapture && listenerEntry.mType.equals(eventImpl.getType())) {
        try {
          listenerEntry.mListener.handleEvent(eventImpl);
        }
 catch (        Exception e) {
          Timber.w(e,"Catched EventListener exception");
        }
      }
    }
  }
  if (eventImpl.getBubbles()) {
  }
  return eventImpl.isPreventDefault();
}
