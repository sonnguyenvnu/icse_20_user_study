@Override public void removeMapping(StubMapping mapping){
  for (  StubLifecycleListener listener : stubLifecycleListeners) {
    listener.beforeStubRemoved(mapping);
  }
  mappings.remove(mapping);
  scenarios.onStubMappingRemoved(mapping);
  for (  StubLifecycleListener listener : stubLifecycleListeners) {
    listener.afterStubRemoved(mapping);
  }
}
