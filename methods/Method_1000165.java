@Override public int save(Article article){
  return articleDao.insertArticle(article);
}
