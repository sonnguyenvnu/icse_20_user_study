public static SModelId regular(String suffix){
  try {
    UUID uuid=UUID.fromString(suffix);
    return regular(uuid);
  }
 catch (  IllegalArgumentException e) {
    long lower=Long.parseLong(suffix);
    UUID uuid=new UUID(0x0000000000004000,lower);
    return regular(uuid);
  }
}
