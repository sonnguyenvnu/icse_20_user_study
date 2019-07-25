private CompatibilityResult run(DataSchema older,DataSchema newer,CompatibilityOptions options){
  _path.clear();
  _checked.clear();
  _options=options;
  _result=new Result();
  check(older,newer);
  return _result;
}
