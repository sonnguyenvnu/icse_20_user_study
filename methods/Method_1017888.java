@JsonIgnore private void dirty(String processorId){
  (updatedProcessors == null ? updatedProcessors=new HashSet<String>() : updatedProcessors).add(processorId);
}
