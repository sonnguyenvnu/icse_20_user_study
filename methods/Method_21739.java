private static String extractCoordinateFromPoint(String point){
  String pointPattern="(\\s*)([0-9\\.-]+)(\\s*)([0-9\\.-]+)(\\s*)";
  return point.replaceAll(pointPattern,"[$2,$4]");
}
