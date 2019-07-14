@Override public void update(final String id,final JSONObject comment,final String... propertyNames) throws RepositoryException {
  super.update(id,comment,propertyNames);
  comment.put(Keys.OBJECT_ID,id);
  commentCache.putComment(comment);
}
