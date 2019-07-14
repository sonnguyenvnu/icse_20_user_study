/** 
 * Get the original line id for a sketch line that was changed at runtime. Used to translate line numbers from the UI at runtime (which can differ from the ones the VM runs on) to their original counterparts.
 * @param line the (possibly) changed runtime line
 * @return the original line or the line given as parameter if not found
 */
protected LineID runtimeToOriginalLine(LineID line){
  for (  Entry<LineID,LineID> entry : runtimeLineChanges.entrySet()) {
    if (entry.getValue().equals(line)) {
      return entry.getKey();
    }
  }
  return line;
}
