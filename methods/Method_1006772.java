/** 
 * ???????????
 * @param task
 * @throws InterruptedException
 */
public static void accept(IAsyncTask task){
  try {
    tasks.put(task);
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
catch (  Exception e) {
    e.printStackTrace();
  }
}
