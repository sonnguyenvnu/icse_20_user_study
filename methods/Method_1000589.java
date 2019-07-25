/** 
 * @param nt
 * @return ???????MAC??
 */
public static String mac(NetworkType nt){
  Map<String,NetworkItem> netFaces=networkItems();
  if (netFaces.isEmpty()) {
    return null;
  }
  List<NetworkItem> list=getNetworkByTypes(netFaces,ntMap.get(nt));
  for (  NetworkItem item : list) {
    if (!Strings.isBlank(item.getMac()))     return item.getMac();
  }
  return null;
}
