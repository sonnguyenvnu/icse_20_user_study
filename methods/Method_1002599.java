public void reset(){
  state=State.FRAME_START;
  payloadRead=0;
  idx=0;
  opcode=-1;
  content=null;
  framePayloadIndex=0;
}
