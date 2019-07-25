@JsonIgnore private void clean(String processorId){
  if (updatedProcessors != null) {
    updatedProcessors.remove(processorId);
  }
}
