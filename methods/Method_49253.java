public static void throwIfSystemName(JanusGraphSchemaCategory category,String name){
  TypeUtil.checkTypeName(category,name);
  if (SystemTypeManager.isSystemType(name.toLowerCase()) || Token.isSystemName(name))   throw new IllegalArgumentException("Name cannot be in protected namespace: " + name);
  for (  char c : RESERVED_CHARS)   Preconditions.checkArgument(name.indexOf(c) < 0,"Name contains reserved character %s: %s",c,name);
}
