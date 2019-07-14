@VisibleForTesting static boolean isBase64(String prefix){
  if (!prefix.contains(";")) {
    return false;
  }
  String[] parameters=prefix.split(";");
  return parameters[parameters.length - 1].equals("base64");
}
