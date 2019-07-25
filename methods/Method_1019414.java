/** 
 * Creates a  {@link SocksProxy} instance with a string.<br>For example:<br> <ul> <li>host,1080 =  {@link Socks5#Socks5(String,int)}</li> <li>host,1080,root,123456 =  {@link Socks5#Socks5(String,int,Credentials)}</li> <li>host,1080,root,123456,trustKeyStorePath,trustKeyStorePassword = Creates a {@link SSLSocks5} instance</li><li>host,1080,root,123456,trustKeyStorePath,trustKeyStorePassword,keyStorePath, keystorePathPassword = Creates a  {@link SSLSocks5} instance which supports clientauthentication</li> </ul>
 * @param value a string.
 * @return a {@link SocksProxy} instance.
 * @throws UnknownHostException  if the host is unknown.
 * @throws FileNotFoundException if file not found.
 */
public static SocksProxy parse(String value) throws UnknownHostException, FileNotFoundException {
  SocksProxy socks=null;
  String host;
  int port;
  String username;
  String password;
  KeyStoreInfo trustKeyStoreInfo;
  KeyStoreInfo keyStoreInfo;
  SSLConfiguration configuration;
  if (value == null) {
    throw new IllegalArgumentException("Input string can't be null");
  }
  String[] values=value.split(",");
  try {
switch (values.length) {
case 2:
      host=values[0];
    port=Integer.parseInt(values[1]);
  return new Socks5(host,port);
case 4:
host=values[0];
port=Integer.parseInt(values[1]);
username=values[2];
password=values[3];
if (Strings.isNullOrEmpty(username)) {
return new Socks5(host,port);
}
 else {
return new Socks5(host,port,new UsernamePasswordCredentials(username,password));
}
case 6:
host=values[0];
port=Integer.parseInt(values[1]);
username=values[2];
password=values[3];
trustKeyStoreInfo=new KeyStoreInfo(PathUtil.getAbstractPath(values[4]),values[5]);
configuration=new SSLConfiguration(null,trustKeyStoreInfo);
socks=new SSLSocks5(new InetSocketAddress(host,port),configuration);
if (!Strings.isNullOrEmpty(username)) {
socks.setCredentials(new UsernamePasswordCredentials(username,password));
}
return socks;
case 8:
host=values[0];
port=Integer.parseInt(values[1]);
username=values[2];
password=values[3];
trustKeyStoreInfo=new KeyStoreInfo(PathUtil.getAbstractPath(values[4]),values[5]);
keyStoreInfo=new KeyStoreInfo(PathUtil.getAbstractPath(values[6]),values[7]);
configuration=new SSLConfiguration(keyStoreInfo,trustKeyStoreInfo);
socks=new SSLSocks5(new InetSocketAddress(host,port),configuration);
if (!Strings.isNullOrEmpty(username)) {
socks.setCredentials(new UsernamePasswordCredentials(username,password));
}
return socks;
default :
throw new IllegalArgumentException("The input string should be formatted as [HOST]," + "[IP],[USERNAME],[PASSWORD],[TRUST_KEY_STORE],[TRUST_KEY_STORE_PASSWORD]," + "[KEY_STORE],[KEY_STORE_PASSWORD]");
}
}
 catch (NumberFormatException e) {
throw new IllegalArgumentException("Port should be a number between 1 and 65535");
}
}
