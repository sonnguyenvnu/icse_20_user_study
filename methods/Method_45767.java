/** 
 * ????
 * @param size       ????
 * @param isPriority ???????
 * @return ??
 */
public static BlockingQueue<Runnable> buildQueue(int size,boolean isPriority){
  BlockingQueue<Runnable> queue;
  if (size == 0) {
    queue=new SynchronousQueue<Runnable>();
  }
 else {
    if (isPriority) {
      queue=size < 0 ? new PriorityBlockingQueue<Runnable>() : new PriorityBlockingQueue<Runnable>(size);
    }
 else {
      queue=size < 0 ? new LinkedBlockingQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(size);
    }
  }
  return queue;
}
