public ParameterList copy(){
  SimpleParameterList[] copySub=new SimpleParameterList[subparams.length];
  for (int sub=0; sub < subparams.length; ++sub) {
    copySub[sub]=(SimpleParameterList)subparams[sub].copy();
  }
  return new CompositeParameterList(copySub,offsets);
}
