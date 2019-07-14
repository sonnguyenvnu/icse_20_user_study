public static String removePort(String domain){
  int portIndex=domain.indexOf(":");
  if (portIndex != -1) {
    return domain.substring(0,portIndex);
  }
 else {
    return domain;
  }
}
