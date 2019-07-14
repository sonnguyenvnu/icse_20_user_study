public boolean replace(StubMapping existingStubMapping,StubMapping newStubMapping){
  if (mappingSet.remove(existingStubMapping)) {
    mappingSet.add(newStubMapping);
    return true;
  }
  return false;
}
