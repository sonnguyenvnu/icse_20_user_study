static void sort(List<TemplateMappingConfiguration> mappingSet){
  Collections.sort(mappingSet,(o1,o2) -> SNodePointer.serialize(o1.getMappingNode()).compareTo((SNodePointer.serialize(o2.getMappingNode()))));
}
