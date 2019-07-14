private void prepareForHostDetachIfNeeded(){
  if (!hasPreparedForHostDetach) {
    hasPreparedForHostDetach=true;
    for (    Router router : getRouters()) {
      router.prepareForHostDetach();
    }
  }
}
