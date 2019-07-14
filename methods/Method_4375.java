/** 
 * Extracts  {@link SchemeData} instances suitable for the given DRM scheme {@link UUID}.
 * @param drmInitData The {@link DrmInitData} from which to extract the {@link SchemeData}.
 * @param uuid The UUID.
 * @param allowMissingData Whether a {@link SchemeData} with null {@link SchemeData#data} may bereturned.
 * @return The extracted {@link SchemeData} instances, or an empty list if no suitable data ispresent.
 */
private static List<SchemeData> getSchemeDatas(DrmInitData drmInitData,UUID uuid,boolean allowMissingData){
  List<SchemeData> matchingSchemeDatas=new ArrayList<>(drmInitData.schemeDataCount);
  for (int i=0; i < drmInitData.schemeDataCount; i++) {
    SchemeData schemeData=drmInitData.get(i);
    boolean uuidMatches=schemeData.matches(uuid) || (C.CLEARKEY_UUID.equals(uuid) && schemeData.matches(C.COMMON_PSSH_UUID));
    if (uuidMatches && (schemeData.data != null || allowMissingData)) {
      matchingSchemeDatas.add(schemeData);
    }
  }
  return matchingSchemeDatas;
}
