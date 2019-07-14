private void generatedNodes(){
  NavigableMap<String,Set<Feature>> classNameToFeatures=getClassNameToFeatures();
  classNameToFeatures.forEach((className,features) -> {
    String higherKey=classNameToFeatures.higherKey(className);
    boolean isLeaf=(higherKey == null) || !higherKey.startsWith(className);
    TypeSpec nodeSpec=makeNodeSpec(className,isLeaf,features);
    nodeTypes.add(nodeSpec);
  }
);
}
