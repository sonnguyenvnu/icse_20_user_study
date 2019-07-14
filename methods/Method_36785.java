private void renderView(BaseCell cell,View view){
  if (view instanceof ITangramViewLifeCycle) {
    return;
  }
  if (methodMap.get(cell) == null) {
    return;
  }
  for (  Method method : methodMap.get(cell).keySet()) {
    try {
      method.invoke(view,methodMap.get(cell).get(method));
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
