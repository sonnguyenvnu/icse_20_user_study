public static Type checkPrimitiveArray(GenericArrayType genericArrayType){
  Type clz=genericArrayType;
  Type genericComponentType=genericArrayType.getGenericComponentType();
  String prefix="[";
  while (genericComponentType instanceof GenericArrayType) {
    genericComponentType=((GenericArrayType)genericComponentType).getGenericComponentType();
    prefix+=prefix;
  }
  if (genericComponentType instanceof Class<?>) {
    Class<?> ck=(Class<?>)genericComponentType;
    if (ck.isPrimitive()) {
      try {
        if (ck == boolean.class) {
          clz=Class.forName(prefix + "Z");
        }
 else         if (ck == char.class) {
          clz=Class.forName(prefix + "C");
        }
 else         if (ck == byte.class) {
          clz=Class.forName(prefix + "B");
        }
 else         if (ck == short.class) {
          clz=Class.forName(prefix + "S");
        }
 else         if (ck == int.class) {
          clz=Class.forName(prefix + "I");
        }
 else         if (ck == long.class) {
          clz=Class.forName(prefix + "J");
        }
 else         if (ck == float.class) {
          clz=Class.forName(prefix + "F");
        }
 else         if (ck == double.class) {
          clz=Class.forName(prefix + "D");
        }
      }
 catch (      ClassNotFoundException e) {
      }
    }
  }
  return clz;
}
