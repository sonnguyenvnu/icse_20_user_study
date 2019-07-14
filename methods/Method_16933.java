private FieldSpec newKeyField(){
  FieldSpec.Builder fieldSpec=isStrongKeys() ? FieldSpec.builder(kTypeVar,"key",Modifier.VOLATILE) : FieldSpec.builder(keyReferenceType(),"key",Modifier.VOLATILE);
  return fieldSpec.build();
}
