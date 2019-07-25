public static String protect(String result){
  if (result.contains(" ")) {
    return "\"" + result + "\"";
  }
  return result;
}
