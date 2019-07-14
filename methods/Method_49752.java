public LinkAddress makeLinkAddress(){
  if (TextUtils.isEmpty(ipAddress)) {
    Timber.e("makeLinkAddress with empty ipAddress");
    return null;
  }
  return new LinkAddress(NetworkUtilsHelper.numericToInetAddress(ipAddress),prefixLength);
}
