public static boolean canDo(Action action,State state){
  if (action == Action.Shift) {
    return !(!state.bufferEmpty() && state.bufferHead() == state.rootIndex && !state.stackEmpty()) && !state.bufferEmpty() && !state.isEmptyFlag();
  }
 else   if (action == Action.RightArc) {
    if (state.stackEmpty())     return false;
    return !(!state.bufferEmpty() && state.bufferHead() == state.rootIndex) && !state.bufferEmpty() && !state.stackEmpty();
  }
 else   if (action == Action.LeftArc) {
    if (state.stackEmpty() || state.bufferEmpty())     return false;
    if (!state.stackEmpty() && state.stackTop() == state.rootIndex)     return false;
    return state.stackTop() != state.rootIndex && !state.hasHead(state.stackTop()) && !state.stackEmpty();
  }
 else   if (action == Action.Reduce) {
    return !state.stackEmpty() && state.hasHead(state.stackTop()) || !state.stackEmpty() && state.stackSize() == 1 && state.bufferSize() == 0 && state.stackTop() == state.rootIndex;
  }
 else   if (action == Action.Unshift) {
    return !state.stackEmpty() && !state.hasHead(state.stackTop()) && state.isEmptyFlag();
  }
  return false;
}
