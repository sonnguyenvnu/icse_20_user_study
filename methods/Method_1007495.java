/** 
 * The entry point. mb.get() is a blocking call that yields the thread ("pausable")
 */
public void execute() throws Pausable {
  while (true) {
    String s=mb.get();
    if (s.equals("done"))     break;
    System.out.print(s);
  }
  Task.exit(0);
}
