public ObjectProxy make(final IocMaking ing,IocObject iobj){
  IocEventSet iocEventSet=iobj.getEvents();
  ObjectProxy op=new ObjectProxy();
  if (iobj.isSingleton() && null != ing.getObjectName())   ing.getContext().save(iobj.getScope(),ing.getObjectName(),op);
  try {
    DefaultWeaver dw=new DefaultWeaver();
    dw.setListeners(ing.getListeners());
    op.setWeaver(dw);
    ValueProxy[] vps=new ValueProxy[Lang.eleSize(iobj.getArgs())];
    for (int i=0; i < vps.length; i++)     vps[i]=ing.makeValue(iobj.getArgs()[i]);
    dw.setArgs(vps);
    Object[] args=new Object[vps.length];
    boolean hasNullArg=false;
    for (int i=0; i < args.length; i++) {
      args[i]=vps[i].get(ing);
      if (args[i] == null) {
        hasNullArg=true;
      }
    }
    Mirror<?> mirror=null;
    if (iobj.getFactory() != null) {
      final String[] ss=iobj.getFactory().split("#",2);
      if (ss[0].startsWith("$")) {
        dw.setBorning(new Borning<Object>(){
          public Object born(          Object... args){
            Object factoryBean=ing.getIoc().get(null,ss[0].substring(1));
            return Mirror.me(factoryBean).invoke(factoryBean,ss[1],args);
          }
        }
);
      }
 else {
        Mirror<?> mi=Mirror.me(Lang.loadClass(ss[0]));
        Method m;
        if (hasNullArg) {
          m=(Method)Lang.first(mi.findMethods(ss[1],args.length));
          if (m == null)           throw new IocException(ing.getObjectName(),"Factory method not found --> ",iobj.getFactory());
          dw.setBorning(new MethodCastingBorning<Object>(m));
        }
 else {
          m=mi.findMethod(ss[1],args);
          dw.setBorning(new MethodBorning<Object>(m));
        }
        if (iobj.getType() == null)         iobj.setType(m.getReturnType());
      }
      if (iobj.getType() != null)       mirror=ing.getMirrors().getMirror(iobj.getType(),ing.getObjectName());
    }
 else {
      mirror=ing.getMirrors().getMirror(iobj.getType(),ing.getObjectName());
      dw.setBorning((Borning<?>)mirror.getBorning(args));
    }
    if (null != iobj.getEvents()) {
      op.setFetch(createTrigger(mirror,iocEventSet.getFetch()));
      op.setDepose(createTrigger(mirror,iocEventSet.getDepose()));
      dw.setCreate(createTrigger(mirror,iocEventSet.getCreate()));
    }
    Object obj=null;
    if (iobj.isSingleton()) {
      obj=dw.born(ing);
      op.setObj(obj);
    }
    List<IocField> _fields=new ArrayList<IocField>(iobj.getFields().values());
    FieldInjector[] fields=new FieldInjector[_fields.size()];
    for (int i=0; i < fields.length; i++) {
      IocField ifld=_fields.get(i);
      try {
        ValueProxy vp=ing.makeValue(ifld.getValue());
        fields[i]=FieldInjector.create(mirror,ifld.getName(),vp,ifld.isOptional());
      }
 catch (      Exception e) {
        throw Lang.wrapThrow(e,"Fail to eval Injector for field: '%s'",ifld.getName());
      }
    }
    dw.setFields(fields);
    if (null != obj)     dw.fill(ing,obj);
    Object tmp=dw.onCreate(obj);
    if (tmp != null)     op.setObj(tmp);
  }
 catch (  IocException e) {
    ing.getContext().remove(iobj.getScope(),ing.getObjectName());
    ((IocException)e).addBeanNames(ing.getObjectName());
    throw e;
  }
catch (  Throwable e) {
    ing.getContext().remove(iobj.getScope(),ing.getObjectName());
    throw new IocException(ing.getObjectName(),e,"throw Exception when creating");
  }
  return op;
}
