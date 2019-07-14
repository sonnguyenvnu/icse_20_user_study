public static Class<?> getArrayClass(String elementClass,ClassLoader classLoader){
  String name;
  if (elementClass.startsWith("[")) {
    name="[" + elementClass;
  }
 else   if (elementClass.equals("boolean")) {
    name="[Z";
  }
 else   if (elementClass.equals("byte")) {
    name="[B";
  }
 else   if (elementClass.equals("char")) {
    name="[C";
  }
 else   if (elementClass.equals("double")) {
    name="[D";
  }
 else   if (elementClass.equals("float")) {
    name="[F";
  }
 else   if (elementClass.equals("int")) {
    name="[I";
  }
 else   if (elementClass.equals("long")) {
    name="[J";
  }
 else   if (elementClass.equals("short")) {
    name="[S";
  }
 else {
    name="[L" + elementClass + ";";
  }
  return loadClass(name,classLoader);
}
