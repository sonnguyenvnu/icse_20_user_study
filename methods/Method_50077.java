/** 
 * Backfill if already created for boundary checking. We do a trick here for corresponding events where we pretend something is created upon initialized state so that it assumes the corresponding event is DESTROY.
 */
void backfillEvents(){
  @Nullable Lifecycle.Event correspondingEvent;
switch (lifecycle.getCurrentState()) {
case INITIALIZED:
    correspondingEvent=ON_CREATE;
  break;
case CREATED:
correspondingEvent=ON_START;
break;
case STARTED:
case RESUMED:
correspondingEvent=ON_RESUME;
break;
case DESTROYED:
default :
correspondingEvent=ON_DESTROY;
break;
}
eventsObservable.onNext(correspondingEvent);
}
