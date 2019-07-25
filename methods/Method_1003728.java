/** 
 * Creates a sleep operation. <p> Unlike  {@link Thread#sleep(long)}, this method does not block the thread. The thread will be relinquished for use by other executions. <p> The given block will be invoked after the duration has passed. The duration must be non-negative.
 * @param duration the duration this execution should sleep for
 * @since 1.5
 */
static Operation sleep(Duration duration){
  if (duration.isNegative()) {
    throw new IllegalArgumentException("Sleep duration must be non negative (value: " + duration + ")");
  }
 else {
    if (duration.isZero()) {
      return Operation.noop();
    }
 else {
      return Promise.async(down -> {
        try {
          current().getEventLoop().schedule(() -> down.success(null),duration.toNanos(),TimeUnit.NANOSECONDS);
        }
 catch (        Throwable e) {
          down.error(e);
        }
      }
).operation();
    }
  }
}
