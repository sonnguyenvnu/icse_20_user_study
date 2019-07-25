@Override public RepositoryConfiguration build(){
  RepositoryConfiguration repositoryConfiguration;
  final String EMPTY_CONFIG="{}";
  final String KYLO_CATEGORIES_METADATA="kylo-categories-metadata";
  final String KYLO_FEEDS_METADATA="kylo-feeds-metadata";
  final String INDEXES="indexes";
  final String INDEX_PROVIDERS="indexProviders";
  try {
    repositoryConfiguration=RepositoryConfiguration.read(EMPTY_CONFIG);
  }
 catch (  ParsingException|FileNotFoundException e) {
    e.printStackTrace();
    repositoryConfiguration=new RepositoryConfiguration();
  }
  Editor editor=repositoryConfiguration.edit();
  EditableDocument indexesDocument=editor.getOrCreateDocument(INDEXES);
  EditableDocument categoriesIndexDocument=indexesDocument.getOrCreateDocument(KYLO_CATEGORIES_METADATA);
  EditableDocument feedsIndexDocument=indexesDocument.getOrCreateDocument(KYLO_FEEDS_METADATA);
  categoriesIndexDocument.putAll(getCategoriesIndexConfiguration());
  feedsIndexDocument.putAll(getFeedsIndexConfiguration());
  EditableDocument indexProvidersDocument=editor.getOrCreateDocument(INDEX_PROVIDERS);
  EditableDocument localNamedIndexProviderDocument=indexProvidersDocument.getOrCreateDocument(LUCENE);
  localNamedIndexProviderDocument.putAll(getLuceneIndexProviderConfiguration());
  repositoryConfiguration=new RepositoryConfiguration(editor,repositoryConfiguration.getName());
  return repositoryConfiguration;
}
