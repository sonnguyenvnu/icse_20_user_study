private ClassFile make() throws CannotCompileException {
  ClassFile cf=new ClassFile(false,classname,superName);
  cf.setAccessFlags(AccessFlag.PUBLIC);
  setInterfaces(cf,interfaces,hasGetHandler ? Proxy.class : ProxyObject.class);
  ConstPool pool=cf.getConstPool();
  if (!factoryUseCache) {
    FieldInfo finfo=new FieldInfo(pool,DEFAULT_INTERCEPTOR,HANDLER_TYPE);
    finfo.setAccessFlags(AccessFlag.PUBLIC | AccessFlag.STATIC);
    cf.addField(finfo);
  }
  FieldInfo finfo2=new FieldInfo(pool,HANDLER,HANDLER_TYPE);
  finfo2.setAccessFlags(AccessFlag.PRIVATE);
  cf.addField(finfo2);
  FieldInfo finfo3=new FieldInfo(pool,FILTER_SIGNATURE_FIELD,FILTER_SIGNATURE_TYPE);
  finfo3.setAccessFlags(AccessFlag.PUBLIC | AccessFlag.STATIC);
  cf.addField(finfo3);
  FieldInfo finfo4=new FieldInfo(pool,SERIAL_VERSION_UID_FIELD,SERIAL_VERSION_UID_TYPE);
  finfo4.setAccessFlags(AccessFlag.PUBLIC | AccessFlag.STATIC | AccessFlag.FINAL);
  cf.addField(finfo4);
  makeConstructors(classname,cf,pool,classname);
  List<Find2MethodsArgs> forwarders=new ArrayList<Find2MethodsArgs>();
  int s=overrideMethods(cf,pool,classname,forwarders);
  addClassInitializer(cf,pool,classname,s,forwarders);
  addSetter(classname,cf,pool);
  if (!hasGetHandler)   addGetter(classname,cf,pool);
  if (factoryWriteReplace) {
    try {
      cf.addMethod(makeWriteReplace(pool));
    }
 catch (    DuplicateMemberException e) {
    }
  }
  thisClass=null;
  return cf;
}
