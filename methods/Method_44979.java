private String erasePackageInfoFromDesc(String desc){
  String limiters="\\(\\)\\<\\>\\[\\]\\?\\s,";
  desc=desc.replaceAll("(?<=[^" + limiters + "]*)([^" + limiters + "]*)\\.","");
  return desc;
}
