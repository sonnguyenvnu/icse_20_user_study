@Override public State<S,E> entry(StateContext<S,E> context){
  if (state == null) {
    if (defaultState.getState() == null) {
      return containingState.getState();
    }
 else {
      return defaultState.getState();
    }
  }
 else {
    if (defaultState.getState() != null && state.getPseudoState() != null && state.getPseudoState().getKind() == PseudoStateKind.END) {
      return defaultState.getState();
    }
 else {
      return state;
    }
  }
}
