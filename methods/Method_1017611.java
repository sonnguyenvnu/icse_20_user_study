/** 
 * <p>Try to make a database connection to the given URL. The driver should return "null" if it realizes it is the wrong kind of driver to connect to the given URL. This will be common, as when the JDBC driverManager is asked to connect to a given URL, it passes the URL to each loaded driver in turn.</p> <p>The driver should raise an SQLException if it is the right driver to connect to the given URL, but has trouble connecting to the database.</p> <p>The java.util.Properties argument can be used to pass arbitrary string tag/value pairs as connection arguments.</p> <ul> <li>user - (required) The user to connect as</li> <li>password - (optional) The password for the user</li> <li>ssl -(optional) Use SSL when connecting to the server</li> <li>readOnly - (optional) Set connection to read-only by default</li> <li>charSet - (optional) The character set to be used for converting to/from the database to unicode. If multibyte is enabled on the server then the character set of the database is used as the default, otherwise the jvm character encoding is used as the default. This value is only used when connecting to a 7.2 or older server.</li> <li>loglevel - (optional) Enable logging of messages from the driver. The value is an integer from 0 to 2 where: OFF = 0, INFO =1, DEBUG = 2 The output is sent to DriverManager.getPrintWriter() if set, otherwise it is sent to System.out.</li> <li>compatible - (optional) This is used to toggle between different functionality as it changes across different releases of the jdbc driver code. The values here are versions of the jdbc client and not server versions. For example in 7.1 get/setBytes worked on LargeObject values, in 7.2 these methods were changed to work on bytea values. This change in functionality could be disabled by setting the compatible level to be "7.1", in which case the driver will revert to the 7.1 functionality.</li> </ul> <p>Normally, at least "user" and "password" properties should be included in the properties. For a list of supported character encoding , see http://java.sun.com/products/jdk/1.2/docs/guide/internat/encoding.doc.html Note that you will probably want to have set up the Postgres database itself to use the same encoding, with the {@code -E <encoding>} argument to createdb.</p><p>Our protocol takes the forms:</p> <pre> jdbc:postgresql://host:port/database?param1=val1&amp;... </pre>
 * @param url the URL of the database to connect to
 * @param info a list of arbitrary tag/value pairs as connection arguments
 * @return a connection to the URL or null if it isnt us
 * @exception SQLException if a database access error occurs or the url is{@code null}
 * @see java.sql.Driver#connect
 */
@Override public Connection connect(String url,Properties info) throws SQLException {
  if (url == null) {
    throw new SQLException("url is null");
  }
  Properties defaults;
  if (!url.startsWith("jdbc:postgresql:")) {
    return null;
  }
  try {
    defaults=getDefaultProperties();
  }
 catch (  IOException ioe) {
    throw new PSQLException(GT.tr("Error loading default settings from driverconfig.properties"),PSQLState.UNEXPECTED_ERROR,ioe);
  }
  Properties props=new Properties(defaults);
  if (info != null) {
    Set<String> e=info.stringPropertyNames();
    for (    String propName : e) {
      String propValue=info.getProperty(propName);
      if (propValue == null) {
        throw new PSQLException(GT.tr("Properties for the driver contains a non-string value for the key ") + propName,PSQLState.UNEXPECTED_ERROR);
      }
      props.setProperty(propName,propValue);
    }
  }
  if ((props=parseURL(url,props)) == null) {
    return null;
  }
  try {
    setupLoggerFromProperties(props);
    LOGGER.log(Level.FINE,"Connecting with URL: {0}",url);
    long timeout=timeout(props);
    if (timeout <= 0) {
      return makeConnection(url,props);
    }
    ConnectThread ct=new ConnectThread(url,props);
    Thread thread=new Thread(ct,"PostgreSQL JDBC driver connection thread");
    thread.setDaemon(true);
    thread.start();
    return ct.getResult(timeout);
  }
 catch (  PSQLException ex1) {
    LOGGER.log(Level.FINE,"Connection error: ",ex1);
    throw ex1;
  }
catch (  java.security.AccessControlException ace) {
    throw new PSQLException(GT.tr("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to."),PSQLState.UNEXPECTED_ERROR,ace);
  }
catch (  Exception ex2) {
    LOGGER.log(Level.FINE,"Unexpected connection error: ",ex2);
    throw new PSQLException(GT.tr("Something unusual has occurred to cause the driver to fail. Please report this exception."),PSQLState.UNEXPECTED_ERROR,ex2);
  }
}
