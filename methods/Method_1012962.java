public static String create(String target){
  if (target == null) {
    return null;
  }
  return new String(DigestUtils.md5(target),Charset.forName("UTF-8"));
}
