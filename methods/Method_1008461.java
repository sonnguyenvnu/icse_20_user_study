public IndexAnalyzers build(IndexSettings indexSettings,Map<String,AnalyzerProvider<?>> analyzerProviders,Map<String,AnalyzerProvider<?>> normalizerProviders,Map<String,TokenizerFactory> tokenizerFactoryFactories,Map<String,CharFilterFactory> charFilterFactoryFactories,Map<String,TokenFilterFactory> tokenFilterFactoryFactories){
  Index index=indexSettings.getIndex();
  analyzerProviders=new HashMap<>(analyzerProviders);
  Logger logger=ServerLoggers.getLogger(getClass(),indexSettings.getSettings());
  DeprecationLogger deprecationLogger=new DeprecationLogger(logger);
  Map<String,NamedAnalyzer> analyzerAliases=new HashMap<>();
  Map<String,NamedAnalyzer> analyzers=new HashMap<>();
  Map<String,NamedAnalyzer> normalizers=new HashMap<>();
  for (  Map.Entry<String,AnalyzerProvider<?>> entry : analyzerProviders.entrySet()) {
    processAnalyzerFactory(deprecationLogger,indexSettings,entry.getKey(),entry.getValue(),analyzerAliases,analyzers,tokenFilterFactoryFactories,charFilterFactoryFactories,tokenizerFactoryFactories);
  }
  for (  Map.Entry<String,AnalyzerProvider<?>> entry : normalizerProviders.entrySet()) {
    processNormalizerFactory(deprecationLogger,indexSettings,entry.getKey(),entry.getValue(),normalizers,tokenizerFactoryFactories.get("keyword"),tokenFilterFactoryFactories,charFilterFactoryFactories);
  }
  for (  Map.Entry<String,NamedAnalyzer> entry : analyzerAliases.entrySet()) {
    String key=entry.getKey();
    if (analyzers.containsKey(key) && ("default".equals(key) || "default_search".equals(key) || "default_search_quoted".equals(key)) == false) {
      throw new IllegalStateException("already registered analyzer with name: " + key);
    }
 else {
      NamedAnalyzer configured=entry.getValue();
      analyzers.put(key,configured);
    }
  }
  if (!analyzers.containsKey("default")) {
    processAnalyzerFactory(deprecationLogger,indexSettings,"default",new StandardAnalyzerProvider(indexSettings,null,"default",Settings.Builder.EMPTY_SETTINGS),analyzerAliases,analyzers,tokenFilterFactoryFactories,charFilterFactoryFactories,tokenizerFactoryFactories);
  }
  if (!analyzers.containsKey("default_search")) {
    analyzers.put("default_search",analyzers.get("default"));
  }
  if (!analyzers.containsKey("default_search_quoted")) {
    analyzers.put("default_search_quoted",analyzers.get("default_search"));
  }
  NamedAnalyzer defaultAnalyzer=analyzers.get("default");
  if (defaultAnalyzer == null) {
    throw new IllegalArgumentException("no default analyzer configured");
  }
  if (analyzers.containsKey("default_index")) {
    final Version createdVersion=indexSettings.getIndexVersionCreated();
    if (createdVersion.onOrAfter(Version.V_5_0_0_alpha1)) {
      throw new IllegalArgumentException("setting [index.analysis.analyzer.default_index] is not supported anymore, use [index.analysis.analyzer.default] instead for index [" + index.getName() + "]");
    }
 else {
      deprecationLogger.deprecated("setting [index.analysis.analyzer.default_index] is deprecated, use [index.analysis.analyzer.default] instead for index [{}]",index.getName());
    }
  }
  NamedAnalyzer defaultIndexAnalyzer=analyzers.containsKey("default_index") ? analyzers.get("default_index") : defaultAnalyzer;
  NamedAnalyzer defaultSearchAnalyzer=analyzers.containsKey("default_search") ? analyzers.get("default_search") : defaultAnalyzer;
  NamedAnalyzer defaultSearchQuoteAnalyzer=analyzers.containsKey("default_search_quote") ? analyzers.get("default_search_quote") : defaultSearchAnalyzer;
  for (  Map.Entry<String,NamedAnalyzer> analyzer : analyzers.entrySet()) {
    if (analyzer.getKey().startsWith("_")) {
      throw new IllegalArgumentException("analyzer name must not start with '_'. got \"" + analyzer.getKey() + "\"");
    }
  }
  return new IndexAnalyzers(indexSettings,defaultIndexAnalyzer,defaultSearchAnalyzer,defaultSearchQuoteAnalyzer,unmodifiableMap(analyzers),unmodifiableMap(normalizers));
}
