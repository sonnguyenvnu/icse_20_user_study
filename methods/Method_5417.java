/** 
 * Creates a playlist with a single variant.
 * @param variantUrl The url of the single variant.
 * @return A master playlist with a single variant for the provided url.
 */
public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String variantUrl){
  List<HlsUrl> variant=Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(variantUrl));
  List<HlsUrl> emptyList=Collections.emptyList();
  return new HlsMasterPlaylist(null,Collections.emptyList(),variant,emptyList,emptyList,null,null,false,Collections.emptyMap());
}
