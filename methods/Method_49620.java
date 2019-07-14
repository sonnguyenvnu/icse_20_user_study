@Override public void setup(final Map<String,Object> config){
  logger.info("Initializing authentication with the {}",this.getClass().getName());
  Preconditions.checkArgument(config != null,String.format("Could not configure a %s - provide a 'config' in the 'authentication' settings",this.getClass().getName()));
  Preconditions.checkState(config.containsKey(CONFIG_CREDENTIALS_DB),String.format("Credential configuration missing the %s key that points to a graph config file or graph name",CONFIG_CREDENTIALS_DB));
  Preconditions.checkState(config.containsKey(CONFIG_DEFAULT_USER),String.format("Credential configuration missing the %s key for the default user",CONFIG_DEFAULT_USER));
  Preconditions.checkState(config.containsKey(CONFIG_DEFAULT_PASSWORD),String.format("Credential configuration missing the %s key for the default password",CONFIG_DEFAULT_PASSWORD));
  final JanusGraph graph=openGraph(config.get(CONFIG_CREDENTIALS_DB).toString());
  credentials=createCredentials(graph);
  graph.tx().rollback();
  ManagementSystem mgmt=(ManagementSystem)graph.openManagement();
  if (!mgmt.containsGraphIndex(USERNAME_INDEX_NAME)) {
    final PropertyKey username=mgmt.makePropertyKey(PROPERTY_USERNAME).dataType(String.class).cardinality(Cardinality.SINGLE).make();
    mgmt.buildIndex(USERNAME_INDEX_NAME,Vertex.class).addKey(username).unique().buildCompositeIndex();
    mgmt.commit();
    mgmt=(ManagementSystem)graph.openManagement();
    final JanusGraphIndex index=mgmt.getGraphIndex(USERNAME_INDEX_NAME);
    if (!index.getIndexStatus(username).equals(SchemaStatus.ENABLED)) {
      try {
        mgmt=(ManagementSystem)graph.openManagement();
        mgmt.updateIndex(mgmt.getGraphIndex(USERNAME_INDEX_NAME),SchemaAction.REINDEX);
        ManagementSystem.awaitGraphIndexStatus(graph,USERNAME_INDEX_NAME).status(SchemaStatus.ENABLED).call();
        mgmt.commit();
      }
 catch (      InterruptedException rude) {
        mgmt.rollback();
        throw new RuntimeException("Timed out waiting for byUsername index to be created on credential graph",rude);
      }
    }
  }
  final String defaultUser=config.get(CONFIG_DEFAULT_USER).toString();
  if (!userExists(defaultUser)) {
    createUser(defaultUser,config.get(CONFIG_DEFAULT_PASSWORD).toString());
  }
}
