public void isValidTypeModifierDefinition(Set<TypeDefinitionCategory> legalTypes){
  Preconditions.checkArgument(1 == this.size(),"exactly one type modifier is expected");
  for (  TypeDefinitionCategory type : this.keySet()) {
    Preconditions.checkArgument(legalTypes.contains(type),"%s not legal here");
  }
}
