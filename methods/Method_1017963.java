public void add(RemoteProcessGroupDTO processGroupDTO){
  remoteProcessGroupCache.computeIfAbsent(processGroupDTO.getId(),groupId -> processGroupDTO);
}
