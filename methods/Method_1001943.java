private static String dotted(int nb){
  final String minor="" + nb % MAJOR_SEPARATOR;
  final String major="" + nb / MAJOR_SEPARATOR;
  return major + "." + minor.substring(0,4) + "." + minor.substring(4);
}
