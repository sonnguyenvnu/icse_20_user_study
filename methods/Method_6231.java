public static boolean isInternalUrl(String url,boolean[] forceBrowser){
  return isInternalUri(Uri.parse(url),forceBrowser);
}
