private void generateLocalCaches(){
  NavigableMap<String,Set<Feature>> classNameToFeatures=getClassNameToFeatures();
  classNameToFeatures.forEach((className,features) -> {
    String higherKey=classNameToFeatures.higherKey(className);
    boolean isLeaf=(higherKey == null) || !higherKey.startsWith(className);
    TypeSpec cacheSpec=makeLocalCacheSpec(className,isLeaf,features);
    factoryTypes.add(cacheSpec);
  }
);
}
