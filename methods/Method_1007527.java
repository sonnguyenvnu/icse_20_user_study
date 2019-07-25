/** 
 * @param millis to sleep. Like thread.sleep, except it doesn't throw an interrupt, and it doesn't hog the java thread.
 */
public static void sleep(final long millis) throws Pausable {
  final Mailbox<Integer> sleepmb=new Mailbox<Integer>(1);
  sleepmb.get(millis);
}
