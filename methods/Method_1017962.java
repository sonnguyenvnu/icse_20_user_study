public void add(ProcessGroupDTO processGroupDTO){
  processGroupCache.computeIfAbsent(processGroupDTO.getId(),groupId -> processGroupDTO);
}
