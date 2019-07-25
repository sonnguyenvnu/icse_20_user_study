private void cleanup(ProcessGroupAndConnections groupAndConnections){
  stopPorts(groupAndConnections.getPorts());
  groupAndConnections.getConnections().stream().forEach(connectionDTO -> restClient.deleteConnection(connectionDTO,false));
  log.info("About to delete {}",groupAndConnections.getProcessGroup().getName());
  restClient.deleteProcessGroup(groupAndConnections.getProcessGroup());
  deletedProcessGroups.add(groupAndConnections.getProcessGroup());
}
