/** 
 * Merges  {@link DrmInitData} obtained from a media manifest and a media stream.<p>The result is generated as follows. <ol> <li>Include all  {@link SchemeData}s from  {@code manifestData} where {@link SchemeData#hasData()} is true.<li>Include all  {@link SchemeData}s in  {@code mediaData} where {@link SchemeData#hasData()}is true and for which we did not include an entry from the manifest targeting the same UUID. <li>If available, the scheme type from the manifest is used. If not, the scheme type from the media is used. </ol>
 * @param manifestData DRM session acquisition data obtained from the manifest.
 * @param mediaData DRM session acquisition data obtained from the media.
 * @return A {@link DrmInitData} obtained from merging a media manifest and a media stream.
 */
public static @Nullable DrmInitData createSessionCreationData(@Nullable DrmInitData manifestData,@Nullable DrmInitData mediaData){
  ArrayList<SchemeData> result=new ArrayList<>();
  String schemeType=null;
  if (manifestData != null) {
    schemeType=manifestData.schemeType;
    for (    SchemeData data : manifestData.schemeDatas) {
      if (data.hasData()) {
        result.add(data);
      }
    }
  }
  if (mediaData != null) {
    if (schemeType == null) {
      schemeType=mediaData.schemeType;
    }
    int manifestDatasCount=result.size();
    for (    SchemeData data : mediaData.schemeDatas) {
      if (data.hasData() && !containsSchemeDataWithUuid(result,manifestDatasCount,data.uuid)) {
        result.add(data);
      }
    }
  }
  return result.isEmpty() ? null : new DrmInitData(schemeType,result);
}
