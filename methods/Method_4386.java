private static UUID adjustUuid(UUID uuid){
  return Util.SDK_INT < 27 && C.CLEARKEY_UUID.equals(uuid) ? C.COMMON_PSSH_UUID : uuid;
}
