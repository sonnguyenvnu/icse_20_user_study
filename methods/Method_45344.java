public static String help(){
  String separator=System.getProperty("line.separator");
  return "Moco Options:" + separator + "moco [server type] -p port -c [configuration file]" + separator + separator + "server type: http, https, socket";
}
