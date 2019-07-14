/** 
 * Get the runtime-changed line id for an original sketch line. Used to translate line numbers from the VM (which runs on the original line numbers) to their current (possibly changed) counterparts.
 * @param line the original line id (at compile time)
 * @return the changed version or the line given as parameter if not found
 */
protected LineID originalToRuntimeLine(LineID line){
  LineID transformed=runtimeLineChanges.get(line);
  if (transformed == null) {
    return line;
  }
  return transformed;
}
