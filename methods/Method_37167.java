private void findClickMethods(Method[] methods){
  for (  Method method : methods) {
    String methodName=method.getName();
    if (isValidMethodName(methodName)) {
      int modifiers=method.getModifiers();
      if ((modifiers & Modifier.PUBLIC) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
        Class<?>[] parameterTypes=method.getParameterTypes();
        if (parameterTypes.length == 3 || parameterTypes.length == 4) {
          Class<?> viewType=parameterTypes[0];
          Class<?> cellType=parameterTypes[1];
          Class<?> clickIntType=parameterTypes[2];
          if (View.class.isAssignableFrom(viewType) && BaseCell.class.isAssignableFrom(cellType) && (clickIntType.equals(int.class) || clickIntType.equals(Integer.class))) {
            if (parameterTypes.length == 4) {
              Class<?> clickParamsType=parameterTypes[3];
              if (Map.class.isAssignableFrom(clickParamsType)) {
                mOnClickMethods.put(viewType,new OnClickMethod(4,method));
              }
            }
 else {
              mOnClickMethods.put(viewType,new OnClickMethod(3,method));
            }
          }
        }
      }
    }
  }
}
