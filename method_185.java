@PostConstruct public void _XXXXX_(){
  if (!StringUtils.equals(repositorySessionFactoryBean.getId(),"cassandra")) {
    return;
  }
  final CassandraHostConfigurator configurator=new CassandraHostConfigurator(cassandraHost + ":" + cassandraPort);
  configurator.setMaxActive(maxActive);
  cluster=HFactory.getOrCreateCluster(clusterName,configurator);
  final ConfigurableConsistencyLevel consistencyLevelPolicy=new ConfigurableConsistencyLevel();
  consistencyLevelPolicy.setDefaultReadConsistencyLevel(HConsistencyLevel.valueOf(readConsistencyLevel));
  consistencyLevelPolicy.setDefaultWriteConsistencyLevel(HConsistencyLevel.valueOf(writeConsistencyLevel));
  keyspace=HFactory.createKeyspace(keyspaceName,cluster,consistencyLevelPolicy);
  List<ColumnFamilyDefinition> cfds=new ArrayList<>();
{
    final ColumnFamilyDefinition namespace=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getNamespaceFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(namespace);
    BasicColumnDefinition nameColumn=new BasicColumnDefinition();
    nameColumn.setName(StringSerializer.get().toByteBuffer(NAME.toString()));
    nameColumn.setIndexName(NAME.toString());
    nameColumn.setIndexType(ColumnIndexType.KEYS);
    nameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    namespace.addColumnDefinition(nameColumn);
    BasicColumnDefinition repositoryIdColumn=new BasicColumnDefinition();
    repositoryIdColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    repositoryIdColumn.setIndexName(REPOSITORY_NAME.toString());
    repositoryIdColumn.setIndexType(ColumnIndexType.KEYS);
    repositoryIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    namespace.addColumnDefinition(repositoryIdColumn);
  }
{
    final ColumnFamilyDefinition repository=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getRepositoryFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(repository);
    BasicColumnDefinition nameColumn=new BasicColumnDefinition();
    nameColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    nameColumn.setIndexName(REPOSITORY_NAME.toString());
    nameColumn.setIndexType(ColumnIndexType.KEYS);
    nameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    repository.addColumnDefinition(nameColumn);
  }
{
    final ColumnFamilyDefinition project=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getProjectFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(project);
    BasicColumnDefinition projectIdColumn=new BasicColumnDefinition();
    projectIdColumn.setName(StringSerializer.get().toByteBuffer(PROJECT_ID.toString()));
    projectIdColumn.setIndexName(PROJECT_ID.toString());
    projectIdColumn.setIndexType(ColumnIndexType.KEYS);
    projectIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    project.addColumnDefinition(projectIdColumn);
    BasicColumnDefinition repositoryIdColumn=new BasicColumnDefinition();
    repositoryIdColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    repositoryIdColumn.setIndexName(REPOSITORY_NAME.toString());
    repositoryIdColumn.setIndexType(ColumnIndexType.KEYS);
    repositoryIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    project.addColumnDefinition(repositoryIdColumn);
    BasicColumnDefinition namespaceIdColumn=new BasicColumnDefinition();
    namespaceIdColumn.setName(StringSerializer.get().toByteBuffer(NAMESPACE_ID.toString()));
    namespaceIdColumn.setIndexName(NAMESPACE_ID.toString());
    namespaceIdColumn.setIndexType(ColumnIndexType.KEYS);
    namespaceIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    project.addColumnDefinition(namespaceIdColumn);
  }
{
    final ColumnFamilyDefinition projectVersionMetadataModel=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getProjectVersionMetadataFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(projectVersionMetadataModel);
    BasicColumnDefinition namespaceIdColumn=new BasicColumnDefinition();
    namespaceIdColumn.setName(StringSerializer.get().toByteBuffer(NAMESPACE_ID.toString()));
    namespaceIdColumn.setIndexName(NAMESPACE_ID.toString());
    namespaceIdColumn.setIndexType(ColumnIndexType.KEYS);
    namespaceIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    projectVersionMetadataModel.addColumnDefinition(namespaceIdColumn);
    BasicColumnDefinition repositoryNameColumn=new BasicColumnDefinition();
    repositoryNameColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    repositoryNameColumn.setIndexName(REPOSITORY_NAME.toString());
    repositoryNameColumn.setIndexType(ColumnIndexType.KEYS);
    repositoryNameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    projectVersionMetadataModel.addColumnDefinition(repositoryNameColumn);
    BasicColumnDefinition idColumn=new BasicColumnDefinition();
    idColumn.setName(StringSerializer.get().toByteBuffer(ID.toString()));
    idColumn.setIndexName(ID.toString());
    idColumn.setIndexType(ColumnIndexType.KEYS);
    idColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    projectVersionMetadataModel.addColumnDefinition(idColumn);
    BasicColumnDefinition projectIdColumn=new BasicColumnDefinition();
    projectIdColumn.setName(StringSerializer.get().toByteBuffer(PROJECT_ID.toString()));
    projectIdColumn.setIndexName(PROJECT_ID.toString());
    projectIdColumn.setIndexType(ColumnIndexType.KEYS);
    projectIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    projectVersionMetadataModel.addColumnDefinition(projectIdColumn);
  }
{
    final ColumnFamilyDefinition artifactMetadataModel=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getArtifactMetadataFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(artifactMetadataModel);
    BasicColumnDefinition idColumn=new BasicColumnDefinition();
    idColumn.setName(StringSerializer.get().toByteBuffer(ID.toString()));
    idColumn.setIndexName(ID.toString());
    idColumn.setIndexType(ColumnIndexType.KEYS);
    idColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(idColumn);
    BasicColumnDefinition repositoryNameColumn=new BasicColumnDefinition();
    repositoryNameColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    repositoryNameColumn.setIndexName(REPOSITORY_NAME.toString());
    repositoryNameColumn.setIndexType(ColumnIndexType.KEYS);
    repositoryNameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(repositoryNameColumn);
    BasicColumnDefinition namespaceIdColumn=new BasicColumnDefinition();
    namespaceIdColumn.setName(StringSerializer.get().toByteBuffer(NAMESPACE_ID.toString()));
    namespaceIdColumn.setIndexName(NAMESPACE_ID.toString());
    namespaceIdColumn.setIndexType(ColumnIndexType.KEYS);
    namespaceIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(namespaceIdColumn);
    BasicColumnDefinition projectColumn=new BasicColumnDefinition();
    projectColumn.setName(StringSerializer.get().toByteBuffer(PROJECT.toString()));
    projectColumn.setIndexName(PROJECT.toString());
    projectColumn.setIndexType(ColumnIndexType.KEYS);
    projectColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(projectColumn);
    BasicColumnDefinition projectVersionColumn=new BasicColumnDefinition();
    projectVersionColumn.setName(StringSerializer.get().toByteBuffer(PROJECT_VERSION.toString()));
    projectVersionColumn.setIndexName(PROJECT_VERSION.toString());
    projectVersionColumn.setIndexType(ColumnIndexType.KEYS);
    projectVersionColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(projectVersionColumn);
    BasicColumnDefinition versionColumn=new BasicColumnDefinition();
    versionColumn.setName(StringSerializer.get().toByteBuffer(VERSION.toString()));
    versionColumn.setIndexName(VERSION.toString());
    versionColumn.setIndexType(ColumnIndexType.KEYS);
    versionColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(versionColumn);
    BasicColumnDefinition whenGatheredColumn=new BasicColumnDefinition();
    whenGatheredColumn.setName(StringSerializer.get().toByteBuffer(WHEN_GATHERED.toString()));
    whenGatheredColumn.setIndexName(WHEN_GATHERED.toString());
    whenGatheredColumn.setIndexType(ColumnIndexType.KEYS);
    whenGatheredColumn.setValidationClass(ComparatorType.LONGTYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(whenGatheredColumn);
    BasicColumnDefinition sha1Column=new BasicColumnDefinition();
    sha1Column.setName(StringSerializer.get().toByteBuffer(SHA1.toString()));
    sha1Column.setIndexName(SHA1.toString());
    sha1Column.setIndexType(ColumnIndexType.KEYS);
    sha1Column.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(sha1Column);
    BasicColumnDefinition md5Column=new BasicColumnDefinition();
    md5Column.setName(StringSerializer.get().toByteBuffer(MD5.toString()));
    md5Column.setIndexName(MD5.toString());
    md5Column.setIndexType(ColumnIndexType.KEYS);
    md5Column.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    artifactMetadataModel.addColumnDefinition(md5Column);
  }
{
    final ColumnFamilyDefinition metadataFacetModel=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getMetadataFacetFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(metadataFacetModel);
    BasicColumnDefinition facetIdColumn=new BasicColumnDefinition();
    facetIdColumn.setName(StringSerializer.get().toByteBuffer(FACET_ID.toString()));
    facetIdColumn.setIndexName(FACET_ID.toString());
    facetIdColumn.setIndexType(ColumnIndexType.KEYS);
    facetIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(facetIdColumn);
    BasicColumnDefinition repositoryNameColumn=new BasicColumnDefinition();
    repositoryNameColumn.setName(StringSerializer.get().toByteBuffer(REPOSITORY_NAME.toString()));
    repositoryNameColumn.setIndexName(REPOSITORY_NAME.toString());
    repositoryNameColumn.setIndexType(ColumnIndexType.KEYS);
    repositoryNameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(repositoryNameColumn);
    BasicColumnDefinition nameColumn=new BasicColumnDefinition();
    nameColumn.setName(StringSerializer.get().toByteBuffer(NAME.toString()));
    nameColumn.setIndexName(NAME.toString());
    nameColumn.setIndexType(ColumnIndexType.KEYS);
    nameColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(nameColumn);
    BasicColumnDefinition namespaceColumn=new BasicColumnDefinition();
    namespaceColumn.setName(StringSerializer.get().toByteBuffer(NAMESPACE_ID.toString()));
    namespaceColumn.setIndexName(NAMESPACE_ID.toString());
    namespaceColumn.setIndexType(ColumnIndexType.KEYS);
    namespaceColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(namespaceColumn);
    BasicColumnDefinition projectIdColumn=new BasicColumnDefinition();
    projectIdColumn.setName(StringSerializer.get().toByteBuffer(PROJECT_ID.toString()));
    projectIdColumn.setIndexName(PROJECT_ID.toString());
    projectIdColumn.setIndexType(ColumnIndexType.KEYS);
    projectIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(projectIdColumn);
    BasicColumnDefinition projectVersionColumn=new BasicColumnDefinition();
    projectVersionColumn.setName(StringSerializer.get().toByteBuffer(PROJECT_VERSION.toString()));
    projectVersionColumn.setIndexName(PROJECT_VERSION.toString());
    projectVersionColumn.setIndexType(ColumnIndexType.KEYS);
    projectVersionColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    metadataFacetModel.addColumnDefinition(projectVersionColumn);
  }
{
    final ColumnFamilyDefinition mailingListCf=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getMailingListFamilyName(),ComparatorType.UTF8TYPE);
    BasicColumnDefinition projectVersionMetadataModel_key=new BasicColumnDefinition();
    projectVersionMetadataModel_key.setName(StringSerializer.get().toByteBuffer("projectVersionMetadataModel.key"));
    projectVersionMetadataModel_key.setIndexName("projectVersionMetadataModel_key");
    projectVersionMetadataModel_key.setIndexType(ColumnIndexType.KEYS);
    projectVersionMetadataModel_key.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    mailingListCf.addColumnDefinition(projectVersionMetadataModel_key);
    cfds.add(mailingListCf);
  }
{
    final ColumnFamilyDefinition licenseCf=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getLicenseFamilyName(),ComparatorType.UTF8TYPE);
    BasicColumnDefinition projectVersionMetadataModel_key=new BasicColumnDefinition();
    projectVersionMetadataModel_key.setName(StringSerializer.get().toByteBuffer("projectVersionMetadataModel.key"));
    projectVersionMetadataModel_key.setIndexName("projectVersionMetadataModel_key");
    projectVersionMetadataModel_key.setIndexType(ColumnIndexType.KEYS);
    projectVersionMetadataModel_key.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    licenseCf.addColumnDefinition(projectVersionMetadataModel_key);
    cfds.add(licenseCf);
  }
{
    final ColumnFamilyDefinition dependencyCf=HFactory.createColumnFamilyDefinition(keyspace.getKeyspaceName(),getDependencyFamilyName(),ComparatorType.UTF8TYPE);
    cfds.add(dependencyCf);
    BasicColumnDefinition groupIdColumn=new BasicColumnDefinition();
    groupIdColumn.setName(StringSerializer.get().toByteBuffer(GROUP_ID.toString()));
    groupIdColumn.setIndexName("groupIdIdx");
    groupIdColumn.setIndexType(ColumnIndexType.KEYS);
    groupIdColumn.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    dependencyCf.addColumnDefinition(groupIdColumn);
    BasicColumnDefinition projectVersionMetadataModel_key=new BasicColumnDefinition();
    projectVersionMetadataModel_key.setName(StringSerializer.get().toByteBuffer("projectVersionMetadataModel.key"));
    projectVersionMetadataModel_key.setIndexName("projectVersionMetadataModel_key");
    projectVersionMetadataModel_key.setIndexType(ColumnIndexType.KEYS);
    projectVersionMetadataModel_key.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
    dependencyCf.addColumnDefinition(projectVersionMetadataModel_key);
  }
{
    if (cluster.describeKeyspace(keyspaceName) == null) {
      logger.info("Creating Archiva Cassandra '{}' keyspace.",keyspaceName);
      cluster.addKeyspace(HFactory.createKeyspaceDefinition(keyspaceName,ThriftKsDef.DEF_STRATEGY_CLASS,replicationFactor,cfds));
    }
  }
}