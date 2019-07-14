private static ArgumentTransformer createArgumentTransformer(Method method,Class<?>[] callbackParameterTypes){
  Class<?>[] targetParameterTypes=method.getParameterTypes();
  int targetParameterLength=targetParameterTypes.length;
  if (targetParameterLength == 0) {
    return ArgumentTransformer.EMPTY;
  }
  int callbackParameterLength=callbackParameterTypes.length;
  if (targetParameterLength > callbackParameterLength) {
    throw new IllegalStateException(method.getDeclaringClass().getName() + "." + method.getName() + " must have at most " + callbackParameterLength + " parameter(s).");
  }
  if (Arrays.equals(targetParameterTypes,callbackParameterTypes)) {
    return ArgumentTransformer.IDENTITY;
  }
  boolean[] callbackIndexUsed=new boolean[callbackParameterLength];
  final int[] indexMap=new int[targetParameterLength];
  nextTarget:   for (int targetIndex=0; targetIndex < targetParameterLength; targetIndex++) {
    Class<?> targetParameterType=targetParameterTypes[targetIndex];
    for (int callbackIndex=0; callbackIndex < callbackParameterLength; callbackIndex++) {
      if (callbackIndexUsed[callbackIndex]) {
        continue;
      }
      Class<?> callbackParameterType=callbackParameterTypes[callbackIndex];
      if (callbackParameterType.equals(targetParameterType) || (View.class.isAssignableFrom(callbackParameterType) && callbackParameterType.isAssignableFrom(targetParameterType)) || targetParameterType.isInterface()) {
        indexMap[targetIndex]=callbackIndex;
        callbackIndexUsed[callbackIndex]=true;
        continue nextTarget;
      }
    }
    StringBuilder builder=new StringBuilder();
    builder.append("Unable to match ").append(method.getDeclaringClass().getName()).append('.').append(method.getName()).append(" method arguments.");
    for (int i=0; i < targetParameterLength; i++) {
      builder.append("\n\n  Parameter #").append(i + 1).append(": ").append(targetParameterTypes[i].getName()).append("\n    ");
      if (i < targetIndex) {
        builder.append("matched listener parameter #").append(indexMap[i]).append(": ").append(callbackParameterTypes[indexMap[i]].getName());
      }
 else {
        builder.append("did not match any listener parameters");
      }
    }
    builder.append("\n\nMethods may have up to ").append(callbackParameterLength).append(" parameter(s):\n");
    for (    Class<?> callbackParameter : callbackParameterTypes) {
      builder.append("\n  ").append(callbackParameter.getName());
    }
    builder.append("\n\nThese may be listed in any order but will be searched for from top to bottom.");
    throw new IllegalStateException(builder.toString());
  }
  return new ArgumentTransformer(){
    @Override public Object[] transform(    Object... arguments){
      Object[] newArguments=new Object[indexMap.length];
      for (int i=0; i < indexMap.length; i++) {
        newArguments[i]=arguments[indexMap[i]];
      }
      return newArguments;
    }
    @Override public String toString(){
      StringBuilder builder=new StringBuilder("ArgumentTransformer[");
      for (int i=0; i < indexMap.length; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        builder.append(i).append(" => ").append(indexMap[i]);
      }
      return builder.append(']').toString();
    }
  }
;
}
