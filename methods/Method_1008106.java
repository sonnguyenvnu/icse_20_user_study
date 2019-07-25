/** 
 * @param type the ec2 hostname type to discover.
 * @return the appropriate host resolved from ec2 meta-data, or null if it cannot be obtained.
 * @see CustomNameResolver#resolveIfPossible(String)
 */
@SuppressForbidden(reason="We call getInputStream in doPrivileged and provide SocketPermission") public InetAddress[] resolve(Ec2HostnameType type) throws IOException {
  InputStream in=null;
  String metadataUrl=AwsEc2ServiceImpl.EC2_METADATA_URL + type.ec2Name;
  try {
    URL url=new URL(metadataUrl);
    logger.debug("obtaining ec2 hostname from ec2 meta-data url {}",url);
    URLConnection urlConnection=SocketAccess.doPrivilegedIOException(url::openConnection);
    urlConnection.setConnectTimeout(2000);
    in=SocketAccess.doPrivilegedIOException(urlConnection::getInputStream);
    BufferedReader urlReader=new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8));
    String metadataResult=urlReader.readLine();
    if (metadataResult == null || metadataResult.length() == 0) {
      throw new IOException("no gce metadata returned from [" + url + "] for [" + type.configName + "]");
    }
    return new InetAddress[]{InetAddress.getByName(metadataResult)};
  }
 catch (  IOException e) {
    throw new IOException("IOException caught when fetching InetAddress from [" + metadataUrl + "]",e);
  }
 finally {
    IOUtils.closeWhileHandlingException(in);
  }
}
