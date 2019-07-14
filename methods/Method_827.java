public static Constructor getKoltinConstructor(Constructor[] constructors,String[] paramNames){
  Constructor creatorConstructor=null;
  for (  Constructor<?> constructor : constructors) {
    Class<?>[] parameterTypes=constructor.getParameterTypes();
    if (paramNames != null && parameterTypes.length != paramNames.length) {
      continue;
    }
    if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) {
      continue;
    }
    if (creatorConstructor != null && creatorConstructor.getParameterTypes().length >= parameterTypes.length) {
      continue;
    }
    creatorConstructor=constructor;
  }
  return creatorConstructor;
}
