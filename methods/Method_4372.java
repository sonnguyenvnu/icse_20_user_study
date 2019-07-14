@Override public boolean canAcquireSession(DrmInitData drmInitData){
  if (offlineLicenseKeySetId != null) {
    return true;
  }
  List<SchemeData> schemeDatas=getSchemeDatas(drmInitData,uuid,true);
  if (schemeDatas.isEmpty()) {
    if (drmInitData.schemeDataCount == 1 && drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
      Log.w(TAG,"DrmInitData only contains common PSSH SchemeData. Assuming support for: " + uuid);
    }
 else {
      return false;
    }
  }
  String schemeType=drmInitData.schemeType;
  if (schemeType == null || C.CENC_TYPE_cenc.equals(schemeType)) {
    return true;
  }
 else   if (C.CENC_TYPE_cbc1.equals(schemeType) || C.CENC_TYPE_cbcs.equals(schemeType) || C.CENC_TYPE_cens.equals(schemeType)) {
    return Util.SDK_INT >= 25;
  }
  return true;
}
