/** 
 * Gets an article's revisions.
 * @param articleId the specified article id
 * @return article revisions, returns an empty list if not found
 */
public List<JSONObject> getArticleRevisions(final String articleId){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Revision.REVISION_DATA_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Revision.REVISION_DATA_TYPE,FilterOperator.EQUAL,Revision.DATA_TYPE_C_ARTICLE))).addSort(Keys.OBJECT_ID,SortDirection.ASCENDING);
  try {
    final List<JSONObject> ret=revisionRepository.getList(query);
    if (ret.isEmpty()) {
      return ret;
    }
    for (    final JSONObject rev : ret) {
      final JSONObject data=new JSONObject(rev.optString(Revision.REVISION_DATA));
      final String articleTitle=Escapes.escapeHTML(data.optString(Article.ARTICLE_TITLE));
      data.put(Article.ARTICLE_TITLE,articleTitle);
      String articleContent=data.optString(Article.ARTICLE_CONTENT);
      articleContent=articleContent.replace("\n","_esc_br_");
      articleContent=Markdowns.clean(articleContent,"");
      articleContent=articleContent.replace("_esc_br_","\n");
      data.put(Article.ARTICLE_CONTENT,articleContent);
      rev.put(Revision.REVISION_DATA,data);
    }
    final JSONObject latestRev=ret.get(ret.size() - 1);
    final JSONObject latestRevData=latestRev.optJSONObject(Revision.REVISION_DATA);
    final String latestRevTitle=latestRevData.optString(Article.ARTICLE_TITLE);
    final String latestRevContent=latestRevData.optString(Article.ARTICLE_CONTENT);
    final JSONObject article=articleRepository.get(articleId);
    final String currentTitle=article.optString(Article.ARTICLE_TITLE);
    String currentContent=article.optString(Article.ARTICLE_CONTENT);
    final boolean titleChanged=!latestRevTitle.replaceAll("\\s+","").equals(currentTitle.replaceAll("\\s+",""));
    final boolean contentChanged=!latestRevContent.replaceAll("\\s+","").equals(currentContent.replaceAll("\\s+",""));
    if (titleChanged || contentChanged) {
      final JSONObject appendRev=new JSONObject();
      final JSONObject appendRevData=new JSONObject();
      appendRev.put(Revision.REVISION_DATA,appendRevData);
      appendRevData.put(Article.ARTICLE_TITLE,Escapes.escapeHTML(currentTitle));
      currentContent=currentContent.replace("\n","_esc_br_");
      currentContent=Markdowns.clean(currentContent,"");
      currentContent=currentContent.replace("_esc_br_","\n");
      appendRevData.put(Article.ARTICLE_CONTENT,currentContent);
      ret.add(appendRev);
    }
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets article revisions failed",e);
    return Collections.emptyList();
  }
}
