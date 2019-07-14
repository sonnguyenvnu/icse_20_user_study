@Override public void update(final String id,final JSONObject article,final String... propertyNames) throws RepositoryException {
  super.update(id,article,propertyNames);
  article.put(Keys.OBJECT_ID,id);
  tagCache.putTag(article);
}
