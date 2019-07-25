/** 
 * @param value the gce hostname type to discover.
 * @return the appropriate host resolved from gce meta-data.
 * @see CustomNameResolver#resolveIfPossible(String)
 */
private InetAddress[] resolve(String value) throws IOException {
  String gceMetadataPath;
  if (value.equals(GceAddressResolverType.GCE.configName)) {
    gceMetadataPath=Strings.replace(GceAddressResolverType.GCE.gceName,"{{network}}","0");
  }
 else   if (value.equals(GceAddressResolverType.PRIVATE_DNS.configName)) {
    gceMetadataPath=GceAddressResolverType.PRIVATE_DNS.gceName;
  }
 else   if (value.startsWith(GceAddressResolverType.PRIVATE_IP.configName)) {
    String network="0";
    String[] privateIpConfig=value.split(":");
    if (privateIpConfig.length == 3) {
      network=privateIpConfig[2];
    }
    gceMetadataPath=Strings.replace(GceAddressResolverType.PRIVATE_IP.gceName,"{{network}}",network);
  }
 else {
    throw new IllegalArgumentException("[" + value + "] is not one of the supported GCE network.host setting. " + "Expecting _gce_, _gce:privateIp:X_, _gce:hostname_");
  }
  try {
    String metadataResult=Access.doPrivilegedIOException(() -> gceMetadataService.metadata(gceMetadataPath));
    if (metadataResult == null || metadataResult.length() == 0) {
      throw new IOException("no gce metadata returned from [" + gceMetadataPath + "] for [" + value + "]");
    }
    return new InetAddress[]{InetAddress.getByName(metadataResult)};
  }
 catch (  IOException e) {
    throw new IOException("IOException caught when fetching InetAddress from [" + gceMetadataPath + "]",e);
  }
}
