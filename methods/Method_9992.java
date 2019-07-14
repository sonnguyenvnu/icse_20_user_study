/** 
 * Organizes the specified comments.
 * @param comments the specified comments
 * @see #organizeComment(JSONObject)
 */
private void organizeComments(final List<JSONObject> comments){
  if (comments.isEmpty()) {
    return;
  }
  Stopwatchs.start("Organizes comments");
  try {
    final ForkJoinPool pool=new ForkJoinPool(Symphonys.PROCESSORS);
    pool.submit(() -> comments.parallelStream().forEach(comment -> {
      try {
        organizeComment(comment);
      }
 catch (      final Exception e) {
        LOGGER.log(Level.ERROR,"Organizes comment [" + comment.optString(Keys.OBJECT_ID) + "] failed",e);
      }
 finally {
        Stopwatchs.release();
      }
    }
));
    pool.shutdown();
    pool.awaitTermination(10,TimeUnit.SECONDS);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Organizes comments failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
