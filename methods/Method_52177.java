private static boolean hasPrefix(String name,String prefix){
  return name.startsWith(prefix) && name.length() > prefix.length() && Character.isUpperCase(name.charAt(prefix.length()));
}
