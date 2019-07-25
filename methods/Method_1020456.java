public static Object unbox(Object object){
  if (object == null) {
    return null;
  }
  BoxedLightEnum<?> boxedEnum=(BoxedLightEnum<?>)object;
  return boxedEnum.value;
}
