public boolean exists() throws IOException {
  if (isFile())   return getFSFile().exists();
  if (isSMB())   try {
    return TimeoutRequest.exists(getSmbFile(),SMB_TIMEOUT);
  }
 catch (  final SmbException e) {
    throw new IOException("SMB.exists SmbException (" + e.getMessage() + ") for " + toNormalform(false));
  }
catch (  final MalformedURLException e) {
    throw new IOException("SMB.exists MalformedURLException (" + e.getMessage() + ") for " + toNormalform(false));
  }
  return false;
}
