public String getParameter(String parameter){
  String value=null;
  for (  String str : responseStr) {
    if (str.startsWith(parameter + '=')) {
      value=str.split("=")[1].trim();
      break;
    }
  }
  return value;
}
