/** 
 * Constructs an instance for the given method, based on the given block order and intermediate local information.
 * @param insns {@code non-null;} instructions to convert
 * @return {@code non-null;} the constructed list
 */
public static LocalList make(DalvInsnList insns){
  int sz=insns.size();
  MakeState state=new MakeState(sz);
  for (int i=0; i < sz; i++) {
    DalvInsn insn=insns.get(i);
    if (insn instanceof LocalSnapshot) {
      RegisterSpecSet snapshot=((LocalSnapshot)insn).getLocals();
      state.snapshot(insn.getAddress(),snapshot);
    }
 else     if (insn instanceof LocalStart) {
      RegisterSpec local=((LocalStart)insn).getLocal();
      state.startLocal(insn.getAddress(),local);
    }
  }
  LocalList result=state.finish();
  if (DEBUG) {
    debugVerify(result);
  }
  return result;
}
