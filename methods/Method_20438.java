static String toSnakeCase(String s){
  return s.replaceAll("([^_A-Z])([A-Z])","$1_$2").toLowerCase();
}
