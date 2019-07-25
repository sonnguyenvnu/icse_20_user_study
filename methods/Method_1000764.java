public void init(NutConfig config,ActionInfo ai) throws Throwable {
  ObjectInfo<? extends ActionFilter>[] filterInfos=ai.getFilterInfos();
  if (null != filterInfos) {
    for (int i=0; i < filterInfos.length; i++) {
      ActionFilter filter=evalObj(config,filterInfos[i]);
      filters.add(filter);
      if (filter instanceof Processor) {
        Processor processor=(Processor)filter;
        if (proxyProcessor == null) {
          proxyProcessor=processor;
          lastProcessor=processor;
        }
 else {
          processor.setNext(proxyProcessor);
          proxyProcessor=processor;
        }
      }
    }
  }
}
