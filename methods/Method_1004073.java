/** 
 * javac generates two switches. First one by  {@link String#hashCode()}. Number of handlers in the second switch is equal to number of handlers in source code, so it is enough to completely filter-out first switch. Handler for default case of the first switch - is the second switch.
 */
private void filter(final AbstractInsnNode start,final IFilterOutput output){
  final LabelNode dflt;
  if (start.getOpcode() == Opcodes.LOOKUPSWITCH) {
    dflt=((LookupSwitchInsnNode)start).dflt;
  }
 else   if (start.getOpcode() == Opcodes.TABLESWITCH) {
    dflt=((TableSwitchInsnNode)start).dflt;
  }
 else {
    return;
  }
  if (new Matcher().match(start,dflt)) {
    output.ignore(start,dflt);
  }
}
