private FieldSpec newValueField(){
  FieldSpec.Builder fieldSpec=isStrongValues() ? FieldSpec.builder(vTypeVar,"value",Modifier.VOLATILE) : FieldSpec.builder(valueReferenceType(),"value",Modifier.VOLATILE);
  return fieldSpec.build();
}
