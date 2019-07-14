public static void unShift(State state){
  if (!state.stackEmpty())   state.setBufferHead(state.pop());
  state.setEmptyFlag(true);
  state.setMaxSentenceSize(state.bufferHead());
}
