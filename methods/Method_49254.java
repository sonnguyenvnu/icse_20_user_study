public static boolean isSystemType(String name){
  return SYSTEM_TYPES_BY_NAME.containsKey(name) || ADDITIONAL_RESERVED_NAMES.contains(name);
}
