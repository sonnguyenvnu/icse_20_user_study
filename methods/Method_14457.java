@JsonIgnore public String getOperationId(){
  return OperationRegistry.s_opClassToName.get(this.getClass());
}
