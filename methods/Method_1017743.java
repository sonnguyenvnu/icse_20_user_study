static ShrinkNode root(FrameworkMethod method,TestClass testClass,List<PropertyParameterGenerationContext> params,Object[] args,long[] seeds,AssertionError failure){
  return new ShrinkNode(method,testClass,params,args,seeds,new int[args.length],failure);
}
