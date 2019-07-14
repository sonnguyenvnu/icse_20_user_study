private void buildTypes(){
  new BuiltinsModule();
  Scope bt=Builtin.getTable();
  Object=newClass("object",bt);
  None=new InstanceType(newClass("None",bt));
  Cont=new InstanceType(newClass("None",bt));
  Type=newClass("type",bt,Object);
  BaseTuple=newClass("tuple",bt,Object);
  BaseList=newClass("list",bt,Object);
  BaseArray=newClass("array",bt);
  BaseDict=newClass("dict",bt,Object);
  ClassType numClass=newClass("int",bt,Object);
  BaseNum=new InstanceType(numClass);
  BaseFloat=new InstanceType(newClass("float",bt,Object));
  BaseComplex=new InstanceType(newClass("complex",bt,Object));
  BaseBool=new InstanceType(newClass("bool",bt,numClass));
  BaseStr=new InstanceType(newClass("str",bt,Object));
  BaseModule=newClass("module",bt);
  BaseFile=newClass("file",bt,Object);
  BaseFunction=newClass("function",bt,Object);
  BaseClass=newClass("classobj",bt,Object);
}
