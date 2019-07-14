public static DocumentBuilderFactory newDocumentBuilderFactory(){
  return new SkipResolvingEntitiesDocumentBuilderFactory();
}
