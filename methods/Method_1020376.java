/** 
 * Performs the conversion.
 * @return {@code non-null;} rop-form output
 */
private RopMethod convert(){
  if (DEBUG) {
    interference.dumpToStdout();
  }
  RegisterAllocator allocator=new FirstFitLocalCombiningAllocator(ssaMeth,interference,minimizeRegisters);
  RegisterMapper mapper=allocator.allocateRegisters();
  if (DEBUG) {
    System.out.println("Printing reg map");
    System.out.println(((BasicRegisterMapper)mapper).toHuman());
  }
  ssaMeth.setBackMode();
  ssaMeth.mapRegisters(mapper);
  removePhiFunctions();
  if (allocator.wantsParamsMovedHigh()) {
    moveParametersToHighRegisters();
  }
  removeEmptyGotos();
  RopMethod ropMethod=new RopMethod(convertBasicBlocks(),ssaMeth.blockIndexToRopLabel(ssaMeth.getEntryBlockIndex()));
  ropMethod=new IdenticalBlockCombiner(ropMethod).process();
  return ropMethod;
}
