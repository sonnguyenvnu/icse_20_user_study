/** 
 * Build the  {@link NifiFlowProcessGroup} from the visited {@link NifiVisitableProcessGroup} returning the simplified graph of objects that make up the flow
 * @param group the visited process group and its flow connecting processors together
 * @return the simplified graph representing the flow starting with the supplied visited process group
 */
public NifiFlowProcessGroup build(NifiVisitableProcessGroup group){
  NifiFlowProcessGroup flowProcessGroup=toFlowProcessGroup(group);
  flowProcessGroup.setProcessorMap(cache);
  flowProcessGroup.addConnections(group.getConnections());
  ProcessGroupDTO groupDTO=group.getParentProcessGroup();
  if (groupDTO != null) {
    flowProcessGroup.setParentGroupId(groupDTO.getId());
    flowProcessGroup.setParentGroupName(groupDTO.getName());
  }
  flowProcessGroup.assignFlowIds();
  return flowProcessGroup;
}
