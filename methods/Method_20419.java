static EpoxyProcessorException buildEpoxyException(String msg,Object... args){
  return new EpoxyProcessorException(String.format(msg,args));
}
