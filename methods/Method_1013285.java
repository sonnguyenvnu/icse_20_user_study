private static String now(){
  if (Boolean.getBoolean(MP.class.getName() + ".noTimestamps")) {
    return "NOW";
  }
  return SDF.format(new Date());
}
