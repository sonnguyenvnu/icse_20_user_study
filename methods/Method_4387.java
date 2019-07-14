private static byte[] adjustRequestInitData(UUID uuid,byte[] initData){
  if ((Util.SDK_INT < 21 && C.WIDEVINE_UUID.equals(uuid)) || (C.PLAYREADY_UUID.equals(uuid) && "Amazon".equals(Util.MANUFACTURER) && ("AFTB".equals(Util.MODEL) || "AFTS".equals(Util.MODEL) || "AFTM".equals(Util.MODEL)))) {
    byte[] psshData=PsshAtomUtil.parseSchemeSpecificData(initData,uuid);
    if (psshData != null) {
      return psshData;
    }
  }
  return initData;
}
