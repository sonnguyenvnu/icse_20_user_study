/** 
 * JVM???????
 * @param jvmName ?? [I;
 * @return ???? ?? int[]
 */
public static String jvmNameToCanonicalName(String jvmName){
  boolean isArray=jvmName.charAt(0) == '[';
  if (isArray) {
    String cnName=StringUtils.EMPTY;
    int i=0;
    for (; i < jvmName.length(); i++) {
      if (jvmName.charAt(i) != '[') {
        break;
      }
      cnName+="[]";
    }
    String componentType=jvmName.substring(i,jvmName.length());
    if ("Z".equals(componentType)) {
      cnName="boolean" + cnName;
    }
 else     if ("B".equals(componentType)) {
      cnName="byte" + cnName;
    }
 else     if ("C".equals(componentType)) {
      cnName="char" + cnName;
    }
 else     if ("D".equals(componentType)) {
      cnName="double" + cnName;
    }
 else     if ("F".equals(componentType)) {
      cnName="float" + cnName;
    }
 else     if ("I".equals(componentType)) {
      cnName="int" + cnName;
    }
 else     if ("J".equals(componentType)) {
      cnName="long" + cnName;
    }
 else     if ("S".equals(componentType)) {
      cnName="short" + cnName;
    }
 else {
      cnName=componentType.substring(1,componentType.length() - 1) + cnName;
    }
    return cnName;
  }
  return jvmName;
}
