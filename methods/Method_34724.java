/** 
 * Queue size rejection threshold is an artificial "max" size at which rejections will occur even if  {@link #maxQueueSize} has not been reached. This is done because the {@link #maxQueueSize} of a{@link BlockingQueue} can not be dynamically changed and we want to support dynamically changing the queue size that affects rejections.<p> This is used by  {@link HystrixCommand} when queuing a thread for execution.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> queueSizeRejectionThreshold(){
  return queueSizeRejectionThreshold;
}
