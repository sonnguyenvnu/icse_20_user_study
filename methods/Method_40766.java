public static String mainName(@NotNull String taggedName){
  String[] segs=taggedName.split(Constants.IDSEP_REGEX);
  if (segs.length == 0) {
    return taggedName;
  }
 else {
    return segs[0];
  }
}
