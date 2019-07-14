/** 
 * Creates a label with the  {@link Label#FLAG_DEBUG_ONLY} flag set, if there is no alreadyexisting label for the given bytecode offset (otherwise does nothing). The label is created with a call to  {@link #readLabel}.
 * @param bytecodeOffset a bytecode offset in a method.
 * @param labels the already created labels, indexed by their offset.
 */
private void createDebugLabel(final int bytecodeOffset,final Label[] labels){
  if (labels[bytecodeOffset] == null) {
    readLabel(bytecodeOffset,labels).flags|=Label.FLAG_DEBUG_ONLY;
  }
}
