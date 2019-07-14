public static String extractKeepAliveMax(final String keepAlive){
  return extractHeaderParameter(keepAlive,"max",',');
}
