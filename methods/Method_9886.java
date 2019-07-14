@Override public void update(final String id,final JSONObject option,final String... propertyNames) throws RepositoryException {
  super.update(id,option,propertyNames);
  option.put(Keys.OBJECT_ID,id);
  optionCache.putOption(option);
}
