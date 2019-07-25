public final Value apply(Value[] args,int control){
  try {
    int alen=this.opDef.getArity();
    if (alen != args.length) {
      Assert.fail("Applying the operator " + Values.ppr(this.toString()) + " with wrong number of arguments.");
    }
    Context c1=this.con;
    FormalParamNode[] formals=this.opDef.getParams();
    for (int i=0; i < alen; i++) {
      c1=c1.cons(formals[i],args[i]);
    }
    return (Value)this.tool.eval(this.opDef.getBody(),c1,this.state,this.pstate,control);
  }
 catch (  RuntimeException|OutOfMemoryError e) {
    if (hasSource()) {
      throw FingerprintException.getNewHead(this,e);
    }
 else {
      throw e;
    }
  }
}
