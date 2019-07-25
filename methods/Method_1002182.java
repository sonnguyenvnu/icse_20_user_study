public static DefaultSearchEngine create(Context context){
  SearchManager searchManager=(SearchManager)context.getSystemService(Context.SEARCH_SERVICE);
  ComponentName name=null;
  if (true)   throw new IllegalStateException("Method getWebSearchActivity not found");
  if (name == null)   return null;
  SearchableInfo searchable=searchManager.getSearchableInfo(name);
  if (searchable == null)   return null;
  return new DefaultSearchEngine(context,searchable);
}
