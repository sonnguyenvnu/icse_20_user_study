public CompositePageProcessor setSubPageProcessors(SubPageProcessor... subPageProcessors){
  this.subPageProcessors=new ArrayList<SubPageProcessor>();
  for (  SubPageProcessor subPageProcessor : subPageProcessors) {
    this.subPageProcessors.add(subPageProcessor);
  }
  return this;
}
