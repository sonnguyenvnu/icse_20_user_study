public Object[] adapt(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,String[] pathArgs){
  Object[] args=new Object[argTypes.length];
  if (args.length != injs.length)   throw new IllegalArgumentException("args.length != injs.length , You get a bug, pls report it!!");
  AdaptorErrorContext errCtx=null;
  if (errCtxIndex > -1)   errCtx=(AdaptorErrorContext)Mirror.me(argTypes[errCtxIndex]).born(argTypes.length);
  Object obj=req.getAttribute(ActionContext.REFER_OBJECT);
  try {
    if (obj == null) {
      obj=getReferObject(sc,req,resp,pathArgs);
      req.setAttribute(ActionContext.REFER_OBJECT,obj);
    }
  }
 catch (  Throwable e) {
    if (errCtx != null) {
      if (log.isInfoEnabled())       log.info("Adapter Error catched , but I found AdaptorErrorContext param, so, set it to args, and continue",e);
      errCtx.setAdaptorError(e,this);
      for (int i=0; i < args.length - 1; i++) {
        if (args[i] == null) {
          if (defaultValues[i] != null) {
            args[i]=Castors.me().castTo(defaultValues[i],argTypes[i]);
          }
 else           if (argTypes[i].isPrimitive()) {
            args[i]=Lang.getPrimitiveDefaultValue(argTypes[i]);
          }
        }
      }
      args[args.length - 1]=errCtx;
      return args;
    }
    throw Lang.wrapThrow(e);
  }
  int curPathArgIdx=0;
  int len=null == pathArgs ? 0 : pathArgs.length;
  for (int i=0; i < args.length; i++) {
    if (AdaptorErrorContext.class.isAssignableFrom(argTypes[i]))     continue;
    Object value=null;
    if (curPathArgIdx < len) {
      value=pathArgs[curPathArgIdx];
      curPathArgIdx++;
    }
 else {
      value=obj;
    }
    try {
      args[i]=injs[i].get(sc,req,resp,value);
    }
 catch (    Throwable e) {
      if (errCtx != null) {
        log.infof("Adapter Param Error(%s) index=%d",method,i,e);
        errCtx.setError(i,e,method,value,injs[i]);
      }
 else       throw Lang.wrapThrow(e);
    }
    if (args[i] == null) {
      if (defaultValues[i] != null) {
        args[i]=Castors.me().castTo(defaultValues[i],argTypes[i]);
      }
 else       if (argTypes[i].isPrimitive()) {
        args[i]=Lang.getPrimitiveDefaultValue(argTypes[i]);
      }
    }
  }
  if (errCtx == null)   return args;
  for (  Throwable err : errCtx.getErrors()) {
    if (err == null)     continue;
    if (errCtxIndex > -1) {
      if (log.isInfoEnabled())       log.info("Adapter Param Error catched , but I found AdaptorErrorContext param, so, set it to args, and continue");
      args[errCtxIndex]=errCtx;
      return args;
    }
    throw Lang.wrapThrow(err);
  }
  return args;
}
