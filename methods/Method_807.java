public static Type unwrapOptional(Type type){
  if (!optionalClassInited) {
    try {
      optionalClass=Class.forName("java.util.Optional");
    }
 catch (    Exception e) {
    }
 finally {
      optionalClassInited=true;
    }
  }
  if (type instanceof ParameterizedType) {
    ParameterizedType parameterizedType=(ParameterizedType)type;
    if (parameterizedType.getRawType() == optionalClass) {
      return parameterizedType.getActualTypeArguments()[0];
    }
  }
  return type;
}
