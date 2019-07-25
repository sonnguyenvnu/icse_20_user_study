/** 
 * Starts a task timing with the specified task title.
 * @param taskTitle the specified task title
 */
public static void start(final String taskTitle){
  Stopwatch root=STOPWATCH.get();
  if (null == root) {
    root=new Stopwatch(taskTitle);
    STOPWATCH.set(root);
    return;
  }
  final Stopwatch recent=getRecentRunning(STOPWATCH.get());
  if (null == recent) {
    return;
  }
  recent.addLeaf(new Stopwatch(taskTitle));
}
