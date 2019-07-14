public CompositePipeline setSubPipeline(SubPipeline... subPipelines){
  this.subPipelines=new ArrayList<SubPipeline>();
  for (  SubPipeline subPipeline : subPipelines) {
    this.subPipelines.add(subPipeline);
  }
  return this;
}
