/** 
 * Pre-ICS Android had a bug resolving HTTPS addresses. This workaround fixes that bug.
 * @param socket The socket to alter
 * @param host   Hostname to connect to
 * @see <a href="https://code.google.com/p/android/issues/detail?id=13117#c14">https://code.google.com/p/android/issues/detail?id=13117#c14</a>
 */
private void injectHostname(Socket socket,String host){
  try {
    if (Integer.valueOf(Build.VERSION.SDK) >= 4) {
      Field field=InetAddress.class.getDeclaredField("hostName");
      field.setAccessible(true);
      field.set(socket.getInetAddress(),host);
    }
  }
 catch (  Exception ignored) {
  }
}
