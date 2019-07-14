private void loadMethod(BaseCell cell,View view){
  if (view instanceof ITangramViewLifeCycle) {
    return;
  }
  if (methodMap.get(cell) != null) {
    return;
  }
  ConcurrentHashMap<Method,Object> paramMap=new ConcurrentHashMap<>();
  Method[] methods;
  if (methodCacheMap.get(view.getClass()) == null) {
    methods=view.getClass().getDeclaredMethods();
    methodCacheMap.put(view.getClass(),methods);
  }
 else {
    methods=methodCacheMap.get(view.getClass());
  }
  CellRender cellRender;
  Class[] paramClazz;
  for (  Method method : methods) {
    cellRender=method.getAnnotation(CellRender.class);
    paramClazz=method.getParameterTypes();
    if (!method.isAnnotationPresent(CellRender.class) || paramClazz == null || paramClazz.length != 1) {
      continue;
    }
    if (method.getName().equals("postBindView")) {
      postBindMap.put(cell,method);
      continue;
    }
    if (method.getName().equals("postUnBindView")) {
      postUnBindMap.put(cell,method);
      continue;
    }
    if (method.getName().equals("cellInited")) {
      cellInitedMap.put(cell,method);
      continue;
    }
    if (!TextUtils.isEmpty(cellRender.key()) && cell.hasParam(cellRender.key())) {
      if ("null".equals(cell.optParam(cellRender.key()))) {
        paramMap.put(method,null);
      }
 else       if (paramClazz[0].equals(Integer.class) || paramClazz[0].equals(int.class)) {
        paramMap.put(method,cell.optIntParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(String.class)) {
        paramMap.put(method,cell.optStringParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(Boolean.class) || paramClazz[0].equals(boolean.class)) {
        paramMap.put(method,cell.optBoolParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(Double.class) || paramClazz[0].equals(double.class)) {
        paramMap.put(method,cell.optDoubleParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(JSONArray.class)) {
        paramMap.put(method,cell.optJsonArrayParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(Long.class) || paramClazz[0].equals(long.class)) {
        paramMap.put(method,cell.optLongParam(cellRender.key()));
      }
 else       if (paramClazz[0].equals(JSONObject.class)) {
        paramMap.put(method,cell.optJsonObjectParam(cellRender.key()));
      }
 else {
        paramMap.put(method,cell.optParam(cellRender.key()));
      }
    }
 else     if (cell.hasParam(method.getName())) {
      if ("null".equals(cell.optParam(method.getName()))) {
        paramMap.put(method,null);
      }
 else       if (paramClazz[0].equals(Integer.class) || paramClazz[0].equals(int.class)) {
        paramMap.put(method,cell.optIntParam(method.getName()));
      }
 else       if (paramClazz[0].equals(String.class)) {
        paramMap.put(method,cell.optStringParam(method.getName()));
      }
 else       if (paramClazz[0].equals(Boolean.class) || paramClazz[0].equals(boolean.class)) {
        paramMap.put(method,cell.optBoolParam(method.getName()));
      }
 else       if (paramClazz[0].equals(Double.class) || paramClazz[0].equals(double.class)) {
        paramMap.put(method,cell.optDoubleParam(method.getName()));
      }
 else       if (paramClazz[0].equals(JSONArray.class)) {
        paramMap.put(method,cell.optJsonArrayParam(method.getName()));
      }
 else       if (paramClazz[0].equals(Long.class) || paramClazz[0].equals(long.class)) {
        paramMap.put(method,cell.optLongParam(method.getName()));
      }
 else       if (paramClazz[0].equals(JSONObject.class)) {
        paramMap.put(method,cell.optJsonObjectParam(method.getName()));
      }
 else {
        paramMap.put(method,cell.optParam(method.getName()));
      }
    }
 else {
      if (paramClazz[0].equals(Integer.class) || paramClazz[0].equals(int.class)) {
        paramMap.put(method,0);
      }
 else       if (paramClazz[0].equals(String.class)) {
        paramMap.put(method,"");
      }
 else       if (paramClazz[0].equals(Boolean.class) || paramClazz[0].equals(boolean.class)) {
        paramMap.put(method,false);
      }
 else       if (paramClazz[0].equals(Double.class) || paramClazz[0].equals(double.class)) {
        paramMap.put(method,0);
      }
 else       if (paramClazz[0].equals(JSONArray.class)) {
        paramMap.put(method,null);
      }
 else       if (paramClazz[0].equals(Long.class) || paramClazz[0].equals(long.class)) {
        paramMap.put(method,0);
      }
 else       if (paramClazz[0].equals(JSONObject.class)) {
        paramMap.put(method,null);
      }
 else {
        paramMap.put(method,"");
      }
    }
  }
  if (!paramMap.isEmpty()) {
    methodMap.put(cell,paramMap);
  }
}
