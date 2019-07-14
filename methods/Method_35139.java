final void prepareForHostDetach(){
  needsAttach=needsAttach || attached;
  for (  ControllerHostedRouter router : childRouters) {
    router.prepareForHostDetach();
  }
}
