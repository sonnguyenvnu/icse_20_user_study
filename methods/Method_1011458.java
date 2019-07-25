public void iteration(){
  for (  SEnumerationLiteral member : ListSequence.fromList(SEnumOperations.getMembers(MetaAdapterFactory.getEnumeration(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xfc6f3944c2L,"jetbrains.mps.lang.structure.structure.Cardinality")))) {
    String value=SEnumOperations.getMemberPresentation(member);
    String name=SEnumOperations.getMemberPresentation(member);
    SEnumerationLiteral cardinality=SEnumOperations.getMemberForPresentation(MetaAdapterFactory.getEnumeration(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xfc6f3944c2L,"jetbrains.mps.lang.structure.structure.Cardinality"),name);
    SEnumerationLiteral cardinality2=SEnumOperations.getMemberForPresentation(MetaAdapterFactory.getEnumeration(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xfc6f3944c2L,"jetbrains.mps.lang.structure.structure.Cardinality"),value);
  }
}
