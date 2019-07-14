public static int getMaskAsInt(String mask){
  if (!validateAgaintIPAdressV4Format(mask)) {
    mask=DEFAULT_MASK;
  }
  return getIpAsInt(mask);
}
