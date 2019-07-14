/** 
 * ?????JVM??
 * @param canonicalName ?? int[]
 * @return JVM?? ?? [I;
 */
public static String canonicalNameToJvmName(String canonicalName){
  boolean isArray=canonicalName.endsWith("[]");
  if (isArray) {
    String t="";
    while (isArray) {
      canonicalName=canonicalName.substring(0,canonicalName.length() - 2);
      t+="[";
      isArray=canonicalName.endsWith("[]");
    }
    if ("boolean".equals(canonicalName)) {
      canonicalName=t + "Z";
    }
 else     if ("byte".equals(canonicalName)) {
      canonicalName=t + "B";
    }
 else     if ("char".equals(canonicalName)) {
      canonicalName=t + "C";
    }
 else     if ("double".equals(canonicalName)) {
      canonicalName=t + "D";
    }
 else     if ("float".equals(canonicalName)) {
      canonicalName=t + "F";
    }
 else     if ("int".equals(canonicalName)) {
      canonicalName=t + "I";
    }
 else     if ("long".equals(canonicalName)) {
      canonicalName=t + "J";
    }
 else     if ("short".equals(canonicalName)) {
      canonicalName=t + "S";
    }
 else {
      canonicalName=t + "L" + canonicalName + ";";
    }
  }
  return canonicalName;
}
