private void suppressInvalidationsOnHosts(boolean suppressInvalidations){
  for (int i=mHostsByMarker.size() - 1; i >= 0; i--) {
    mHostsByMarker.valueAt(i).suppressInvalidations(suppressInvalidations);
  }
}
