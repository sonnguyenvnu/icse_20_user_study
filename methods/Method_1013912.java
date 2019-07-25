@Override public Map<String,Object> execute(Map<String,Object> context){
  Map<String,Object> output=new HashMap<>();
  Annotation[][] annotations=method.getParameterAnnotations();
  List<Object> args=new ArrayList<>();
  for (int i=0; i < annotations.length; i++) {
    Annotation[] annotationsOnParam=annotations[i];
    if (annotationsOnParam != null && annotationsOnParam.length == 1) {
      if (annotationsOnParam[0] instanceof ActionInput) {
        ActionInput inputAnnotation=(ActionInput)annotationsOnParam[0];
        if (hasInput(moduleType,inputAnnotation.name())) {
          args.add(i,context.get(inputAnnotation.name()));
        }
 else {
          logger.error("Annotated method defines input '{}' but the module type '{}' does not specify an input with this name.",inputAnnotation.name(),moduleType);
          return output;
        }
      }
    }
 else {
      args.add(i,context.get("p" + i));
    }
  }
  Object result=null;
  try {
    result=method.invoke(this.actionProvider,args.toArray());
  }
 catch (  IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
    logger.error("Could not call method '{}' from module type '{}'.",method,moduleType.getUID(),e);
  }
  if (result != null) {
    if (result instanceof Map<?,?>) {
      try {
        Map<String,Object> resultMap=(Map<String,Object>)result;
        for (        Entry<String,Object> entry : resultMap.entrySet()) {
          if (hasOutput(moduleType,entry.getKey())) {
            output.put(entry.getKey(),entry.getValue());
          }
        }
      }
 catch (      ClassCastException ex) {
        logger.error("The return type of action method '{}' from module type '{}' should be Map<String, Object>, because {}",method,moduleType.getUID(),ex.getMessage());
      }
    }
 else     if (result instanceof Boolean) {
      output.put(MODULE_RESULT,(boolean)result);
    }
 else     if (result instanceof String) {
      output.put(MODULE_RESULT,result);
    }
 else     if (result instanceof Integer) {
      output.put(MODULE_RESULT,result);
    }
 else     if (result instanceof Double) {
      output.put(MODULE_RESULT,(double)result);
    }
 else     if (result instanceof Float) {
      output.put(MODULE_RESULT,(float)result);
    }
 else {
      logger.warn("Non compatible return type '{}' on action method.",result.getClass());
    }
  }
  return output;
}
