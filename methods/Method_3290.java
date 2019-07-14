public boolean open(String[] args){
  Option cmd=new Option();
  try {
    Args.parse(cmd,args);
  }
 catch (  IllegalArgumentException e) {
    System.err.println("invalid arguments");
    return false;
  }
  String model=cmd.model;
  int nbest=cmd.nbest;
  int vlevel=cmd.verbose;
  double costFactor=cmd.cost_factor;
  return open(model,nbest,vlevel,costFactor);
}
