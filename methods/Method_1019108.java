/** 
 * Gets most recent change, deleting it in the process.
 */
public byte[] pop(){
  times.pop();
  byte[] content=stack.pop();
  if (content != null) {
    classes.remove(name);
    classes.putRaw(name,content);
    Bus.post(new HistoryRevertEvent(name));
    Bus.post(new ClassReloadEvent(name));
    Logging.info("Reverted '" + name + "'");
  }
 else {
    Logging.info("No history for '" + name + "' to revert back to");
  }
  return content;
}
