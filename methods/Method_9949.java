/** 
 * Organizes the specified articles.
 * @param articles the specified articles
 * @see #organizeArticle(org.json.JSONObject)
 */
public void organizeArticles(final List<JSONObject> articles){
  Stopwatchs.start("Organize articles");
  try {
    final ForkJoinPool pool=new ForkJoinPool(Symphonys.PROCESSORS);
    pool.submit(() -> articles.parallelStream().forEach(article -> {
      try {
        organizeArticle(article);
      }
 catch (      final Exception e) {
        LOGGER.log(Level.ERROR,"Organizes article [" + article.optString(Keys.OBJECT_ID) + "] failed",e);
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
    LOGGER.log(Level.ERROR,"Organizes articles failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
