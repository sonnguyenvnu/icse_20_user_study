@Override public RepositoryConfiguration build(){
  RepositoryConfiguration repositoryConfiguration;
  final String EMPTY_CONFIG="{}";
  final String KYLO_CATEGORIES="kylo-categories";
  final String KYLO_FEEDS="kylo-feeds";
  final String KYLO_INTERNAL_FD1="kylo-internal-fd1";
  final String INDEXES="indexes";
  final String INDEX_PROVIDERS="indexProviders";
  try {
    repositoryConfiguration=RepositoryConfiguration.read(EMPTY_CONFIG);
  }
 catch (  ParsingException|FileNotFoundException e) {
    e.printStackTrace();
    return null;
  }
  Editor editor=repositoryConfiguration.edit();
  EditableDocument indexesDocument=editor.getOrCreateDocument(INDEXES);
  EditableDocument categoriesIndexDocument=indexesDocument.getOrCreateDocument(KYLO_CATEGORIES);
  EditableDocument feedsIndexDocument=indexesDocument.getOrCreateDocument(KYLO_FEEDS);
  EditableDocument feeds2IndexDocument=indexesDocument.getOrCreateDocument(KYLO_INTERNAL_FD1);
  categoriesIndexDocument.putAll(getCategoriesIndexConfiguration());
  feedsIndexDocument.putAll(getFeedSummaryIndexConfiguration());
  feeds2IndexDocument.putAll(getFeedDetailsIndexConfiguration());
  EditableDocument indexProvidersDocument=editor.getOrCreateDocument(INDEX_PROVIDERS);
  EditableDocument elasticSearchIndexProviderDocument=indexProvidersDocument.getOrCreateDocument(ELASTIC_SEARCH);
  elasticSearchIndexProviderDocument.putAll(getElasticSearchIndexProviderConfiguration());
  repositoryConfiguration=new RepositoryConfiguration(editor,repositoryConfiguration.getName());
  return repositoryConfiguration;
}
