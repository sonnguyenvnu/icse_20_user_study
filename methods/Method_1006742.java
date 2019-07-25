/** 
 * Flush out the log to stderr, and clear the log contents. Only call this on the toplevel log node, when threads do not have access to references of internal log nodes so that they cannot add more log entries inside the tree, otherwise log entries may be lost.
 */
public void flush(){
  if (parent != null) {
    throw new IllegalArgumentException("Only flush the toplevel LogNode");
  }
  if (!children.isEmpty()) {
    final String logOutput=this.toString();
    this.children.clear();
    log.info(logOutput);
  }
}
