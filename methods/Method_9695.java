/** 
 * Loads side random articles.
 */
public void loadSideRandomArticles(){
  final int size=Symphonys.SIDE_RANDOM_ARTICLES_CNT;
  if (1 > size) {
    return;
  }
  final BeanManager beanManager=BeanManager.getInstance();
  final ArticleRepository articleRepository=beanManager.getReference(ArticleRepository.class);
  final ArticleQueryService articleQueryService=beanManager.getReference(ArticleQueryService.class);
  Stopwatchs.start("Load side random articles");
  try {
    final List<JSONObject> articles=articleRepository.getRandomly(size * 5);
    articleQueryService.organizeArticles(articles);
    SIDE_RANDOM_ARTICLES.clear();
    SIDE_RANDOM_ARTICLES.addAll(articles);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Loads side random articles failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
