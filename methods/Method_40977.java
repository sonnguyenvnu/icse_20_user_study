/** 
 * {@inheritDoc}
 */
@Override public Collection<AgentMetadataEntity> getAgentsMetadata(UriInfo info){
  AgentMetadataEntity ame=new AgentMetadataEntity();
  ame.setAgentId(AgentEntity.EMBEDDED_AGENT_ID);
  ame.setAgencyOf("Quartz");
  ame.setVersion(this.getClass().getPackage().getImplementationVersion());
  ame.setAvailable(true);
  return Collections.singleton(ame);
}
