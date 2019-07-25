/** 
 * Initializes manager if it wasn't already initialized.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener(){
      @Override public void eventDispatched(      final AWTEvent event){
        if (event instanceof FocusEvent) {
          if (globalFocusListeners.size() > 0) {
            final FocusEvent focusEvent=(FocusEvent)event;
            if (focusEvent.getID() == FocusEvent.FOCUS_LOST && focusEvent.getOppositeComponent() == null) {
              fireGlobalFocusChanged(focusEvent.getComponent(),null);
            }
 else             if (focusEvent.getID() == FocusEvent.FOCUS_GAINED) {
              fireGlobalFocusChanged(focusEvent.getOppositeComponent(),focusEvent.getComponent());
            }
          }
        }
      }
    }
,AWTEvent.FOCUS_EVENT_MASK);
    registerGlobalFocusListener(new GlobalFocusListener(){
      @Override public void focusChanged(      final Component oldFocus,      final Component newFocus){
        oldFocusOwner=new WeakReference<Component>(oldFocus);
        focusOwner=new WeakReference<Component>(newFocus);
        if (GlobalConstants.DEBUG) {
          final String oldName=oldFocus != null ? oldFocus.getClass().getName() : null;
          final String newName=newFocus != null ? newFocus.getClass().getName() : null;
          Log.debug(this,"Focus changed: " + oldName + " --> " + newName);
        }
        for (        final Map.Entry<Component,Map<FocusTracker,Boolean>> entry : getTrackersCopy().entrySet()) {
          final Component tracked=entry.getKey();
          if (tracked != null) {
            for (            final Map.Entry<FocusTracker,Boolean> innerEntry : entry.getValue().entrySet()) {
              final FocusTracker focusTracker=innerEntry.getKey();
              if (focusTracker.isTrackingEnabled()) {
                final boolean isOldFocused=focusTracker.isInvolved(oldFocus,tracked);
                final boolean isNewFocused=focusTracker.isInvolved(newFocus,tracked);
                if (isOldFocused || isNewFocused) {
                  final Boolean trackerStateCache=innerEntry.getValue();
                  if (trackerStateCache == null || trackerStateCache != isNewFocused) {
                    focusTracker.focusChanged(isNewFocused);
synchronized (trackersLock) {
                      final Map<FocusTracker,Boolean> ct=trackers.get(tracked);
                      if (ct != null && ct.containsKey(focusTracker)) {
                        ct.put(focusTracker,isNewFocused);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
);
  }
}
