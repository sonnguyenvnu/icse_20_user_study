/** 
 * Built a shcedule context for metadata client service.
 * @return
 */
public IScheduleContext _XXXXX_(){
  topologies=listToMap(client.listTopologies());
  kafkaSources=listToMap(client.listDataSources());
  List<PolicyDefinition> enabledPolicies=client.listPolicies().stream().filter((t) -> t.getPolicyStatus() != PolicyStatus.DISABLED).collect(Collectors.toList());
  policies=listToMap(enabledPolicies);
  publishments=listToMap(client.listPublishment());
  streamDefinitions=listToMap(client.listStreams());
  new NodataMetadataGenerator().execute(config,streamDefinitions,kafkaSources,policies,publishments);
  ScheduleState state=client.getVersionedSpec();
  assignments=listToMap(state == null ? new ArrayList<PolicyAssignment>() : detectAssignmentsChange(state.getAssignments(),state));
  monitoredStreamMap=listToMap(state == null ? new ArrayList<MonitoredStream>() : detectMonitoredStreams(state.getMonitoredStreams()));
  usages=buildTopologyUsage();
  builtContext=new InMemScheduleConext(topologies,assignments,kafkaSources,policies,publishments,streamDefinitions,monitoredStreamMap,usages);
  return builtContext;
}