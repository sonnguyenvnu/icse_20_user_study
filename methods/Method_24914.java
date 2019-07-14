/** 
 * Has the user screwed up their hosts file? https://github.com/processing/processing/issues/4738
 */
static private void checkLocalHost() throws SketchException {
  try {
    InetAddress address=InetAddress.getByName("localhost");
    if (!address.getHostAddress().equals("127.0.0.1")) {
      System.err.println("Your computer is not properly mapping 'localhost' to '127.0.0.1',");
      System.err.println("which prevents sketches from working properly because 'localhost'");
      System.err.println("is needed to connect the PDE to your sketch while it's running.");
      System.err.println("If you don't recall making this change, or know how to fix it:");
      System.err.println("https://www.google.com/search?q=add+localhost+to+hosts+file+" + Platform.getName());
      throw new SketchException("Cannot run due to changes in your 'hosts' file. " + "See the console for details.",false);
    }
  }
 catch (  UnknownHostException e) {
    e.printStackTrace();
  }
}
