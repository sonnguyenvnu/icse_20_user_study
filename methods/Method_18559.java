/** 
 * Sets the initial value for a state or transfers the previous state value to the new component, then applies all the states updates that have been enqueued for the new component's global key. Assumed thread-safe because the one write is before all the reads.
 * @param component the new component
 */
@ThreadSafe(enableChecks=false) void applyStateUpdatesForComponent(Component component){
  maybeInitStateContainers();
  maybeInitNeededStateContainers();
  if (!component.hasState()) {
    return;
  }
  final String key=component.getGlobalKey();
  final StateContainer currentStateContainer;
synchronized (this) {
    currentStateContainer=mStateContainers.get(key);
    mNeededStateContainers.add(key);
  }
  if (currentStateContainer != null) {
    component.transferState(currentStateContainer,component.getStateContainer());
  }
 else {
    final ComponentTree componentTree=component.getScopedContext().getComponentTree();
    if (componentTree == null || !componentTree.shouldCreateInitialStateOncePerThread()) {
      component.createInitialState(component.getScopedContext());
    }
 else {
      maybeCreateInitialStateAndCommitResult(component,componentTree);
    }
  }
  final List<StateUpdate> stateUpdatesForKey;
synchronized (this) {
    stateUpdatesForKey=mPendingStateUpdates == null ? null : mPendingStateUpdates.get(key);
  }
  if (stateUpdatesForKey != null) {
    for (    StateUpdate update : stateUpdatesForKey) {
      component.getStateContainer().applyStateUpdate(update);
    }
    LithoStats.incStateUpdate(stateUpdatesForKey.size());
    if (component.getScopedContext().isNestedTreeResolutionExperimentEnabled()) {
synchronized (this) {
        mPendingStateUpdates.remove(key);
        if (mPendingLazyStateUpdates != null) {
          mPendingLazyStateUpdates.remove(key);
        }
        mAppliedStateUpdates.put(key,stateUpdatesForKey);
      }
    }
  }
synchronized (this) {
    final StateContainer stateContainer=component.getStateContainer();
    mStateContainers.put(key,stateContainer);
    if (stateContainer instanceof ComponentLifecycle.TransitionContainer) {
      final List<Transition> transitions=((ComponentLifecycle.TransitionContainer)stateContainer).consumeTransitions();
      if (!transitions.isEmpty()) {
        maybeInitPendingStateUpdateTransitions();
        mPendingStateUpdateTransitions.put(key,transitions);
      }
    }
  }
}
