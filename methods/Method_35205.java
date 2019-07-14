void prepareForContainerRemoval(){
  containerFullyAttached=false;
  if (container != null) {
    container.setOnHierarchyChangeListener(null);
  }
}
