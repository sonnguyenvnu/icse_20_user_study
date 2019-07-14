private int getHigh(){
  if ("LGE".equals(Build.MANUFACTURER) && "g3_tmo_us".equals(Build.PRODUCT)) {
    return CamcorderProfile.QUALITY_480P;
  }
  return CamcorderProfile.QUALITY_HIGH;
}
