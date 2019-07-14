private void findTraceMethods(Method[] methods){
  for (  Method method : methods) {
    String methodName=method.getName();
    if (isValidTraceMethodName(methodName)) {
      int modifiers=method.getModifiers();
      if ((modifiers & Modifier.PUBLIC) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
        Class<?>[] parameterTypes=method.getParameterTypes();
        if (parameterTypes.length == 3) {
          Class<?> viewType=parameterTypes[0];
          Class<?> cellType=parameterTypes[1];
          Class<?> clickIntType=parameterTypes[2];
          if (View.class.isAssignableFrom(viewType) && BaseCell.class.isAssignableFrom(cellType) && (clickIntType.equals(int.class) || clickIntType.equals(Integer.class))) {
            mOnTraceMethods.put(viewType,new OnTraceMethod(3,method));
          }
        }
      }
    }
  }
}
