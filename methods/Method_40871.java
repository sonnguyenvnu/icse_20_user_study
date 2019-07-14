/** 
 * Transitions to the  {@code newState} if not already in that state and calls any associated event listener.
 */
private void transitionTo(State newState,CheckedRunnable listener){
  boolean transitioned=false;
synchronized (this) {
    if (!getState().equals(newState)) {
switch (newState) {
case CLOSED:
        state.set(new ClosedState(this));
      break;
case OPEN:
    state.set(new OpenState(this,state.get()));
  break;
case HALF_OPEN:
state.set(new HalfOpenState(this));
break;
}
transitioned=true;
}
}
if (transitioned && listener != null) {
try {
listener.run();
}
 catch (Exception ignore) {
}
}
}
