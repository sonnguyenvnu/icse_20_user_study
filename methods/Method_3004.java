public static void shift(State state){
  state.push(state.bufferHead());
  state.incrementBufferHead();
  if (state.bufferEmpty())   state.setEmptyFlag(true);
}
