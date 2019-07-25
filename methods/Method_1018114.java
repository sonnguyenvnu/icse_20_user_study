@Override public RepositoryConfiguration build(){
  RepositoryConfiguration repositoryConfiguration;
  final String EMPTY_CONFIG="{}";
  final String KYLO_CATEGORIES="kylo-categories";
  final String KYLO_FEEDS="kylo-feeds";
  final String INDEXES="indexes";
  final String INDEX_PROVIDERS="indexProviders";
  try {
    repositoryConfiguration=RepositoryConfiguration.read(EMPTY_CONFIG);
  }
 catch (  ParsingException|FileNotFoundException e) {
    log.error("Error loading the repository configuration",e);
    return null;
  }
  Editor editor=repositoryConfiguration.edit();
  EditableDocument indexesDocument=editor.getOrCreateDocument(INDEXES);
  EditableDocument categoriesIndexDocument=indexesDocument.getOrCreateDocument(KYLO_CATEGORIES);
  EditableDocument feedsIndexDocument=indexesDocument.getOrCreateDocument(KYLO_FEEDS);
  categoriesIndexDocument.putAll(getCategoriesIndexConfiguration());
  feedsIndexDocument.putAll(getFeedsIndexConfiguration());
  EditableDocument indexProvidersDocument=editor.getOrCreateDocument(INDEX_PROVIDERS);
  EditableDocument elasticSearchIndexProviderDocument=indexProvidersDocument.getOrCreateDocument(ELASTIC_SEARCH);
  elasticSearchIndexProviderDocument.putAll(getElasticSearchIndexProviderConfiguration());
  repositoryConfiguration=new RepositoryConfiguration(editor,repositoryConfiguration.getName());
  return repositoryConfiguration;
}
