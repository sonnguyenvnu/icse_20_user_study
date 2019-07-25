private static void cleanup(final Set<ConnectionInfo> connectionSet){
  final Iterator<ConnectionInfo> iter=connectionSet.iterator();
synchronized (iter) {
    while (iter.hasNext()) {
      ConnectionInfo con=null;
      try {
        con=iter.next();
      }
 catch (      Throwable e) {
        break;
      }
      try {
        if (con.getLifetime() > staleAfterMillis) {
          connectionSet.remove(con);
        }
      }
 catch (      Throwable e) {
        continue;
      }
    }
  }
}
