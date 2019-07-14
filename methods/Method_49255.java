public void isValidDefinition(Set<TypeDefinitionCategory> requiredTypes){
  Set<TypeDefinitionCategory> keys=this.keySet();
  for (  TypeDefinitionCategory type : requiredTypes) {
    Preconditions.checkArgument(keys.contains(type),"%s not in %s",type,this);
  }
  Preconditions.checkArgument(keys.size() == requiredTypes.size(),"Found irrelevant definitions in: %s",this);
}
