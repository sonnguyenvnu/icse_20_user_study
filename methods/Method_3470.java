public static List<EnumItem<NT>> roleTag(List<Vertex> vertexList,WordNet wordNetAll){
  List<EnumItem<NT>> tagList=new LinkedList<EnumItem<NT>>();
  for (  Vertex vertex : vertexList) {
    Nature nature=vertex.guessNature();
    if (nature == nrf) {
      if (vertex.getAttribute().totalFrequency <= 1000) {
        tagList.add(new EnumItem<NT>(NT.F,1000));
        continue;
      }
    }
 else     if (nature == ni || nature == nic || nature == nis || nature == nit) {
      EnumItem<NT> ntEnumItem=new EnumItem<NT>(NT.K,1000);
      ntEnumItem.addLabel(NT.D,1000);
      tagList.add(ntEnumItem);
      continue;
    }
 else     if (nature == m) {
      EnumItem<NT> ntEnumItem=new EnumItem<NT>(NT.M,1000);
      tagList.add(ntEnumItem);
      continue;
    }
    EnumItem<NT> NTEnumItem=OrganizationDictionary.dictionary.get(vertex.word);
    if (NTEnumItem == null) {
      NTEnumItem=new EnumItem<NT>(NT.Z,OrganizationDictionary.transformMatrixDictionary.getTotalFrequency(NT.Z));
    }
    tagList.add(NTEnumItem);
  }
  return tagList;
}
