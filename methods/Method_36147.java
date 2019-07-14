@Override public void editMapping(StubMapping stubMapping){
  final Optional<StubMapping> optionalExistingMapping=tryFind(mappings,mappingMatchingUuid(stubMapping.getUuid()));
  if (!optionalExistingMapping.isPresent()) {
    String msg="StubMapping with UUID: " + stubMapping.getUuid() + " not found";
    notifier().error(msg);
    throw new RuntimeException(msg);
  }
  final StubMapping existingMapping=optionalExistingMapping.get();
  for (  StubLifecycleListener listener : stubLifecycleListeners) {
    listener.beforeStubEdited(existingMapping,stubMapping);
  }
  stubMapping.setInsertionIndex(existingMapping.getInsertionIndex());
  stubMapping.setDirty(true);
  mappings.replace(existingMapping,stubMapping);
  scenarios.onStubMappingUpdated(existingMapping,stubMapping);
  for (  StubLifecycleListener listener : stubLifecycleListeners) {
    listener.afterStubEdited(existingMapping,stubMapping);
  }
}
