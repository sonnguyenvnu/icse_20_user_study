public ActionChain eval(NutConfig config,ActionInfo ai){
  try {
    List<Processor> list=new ArrayList<Processor>();
    for (    String name : co.getProcessors(ai.getChainName())) {
      Processor processor=getProcessorByName(config,name);
      if (processor != null) {
        processor.init(config,ai);
        list.add(processor);
      }
    }
    Processor errorProcessor=getProcessorByName(config,co.getErrorProcessor(ai.getChainName()));
    errorProcessor.init(config,ai);
    return new NutActionChain(list,errorProcessor,ai);
  }
 catch (  Throwable e) {
    if (log.isDebugEnabled())     log.debugf("Eval FAIL!! : %s",ai.getMethod(),e);
    throw Lang.wrapThrow(e);
  }
}
