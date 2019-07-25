@Override public String resolve(String uri){
  StringBuilder sb=new StringBuilder(picture.length + EMBED_IMG_SRC_PREFIX.length()).append(EMBED_IMG_SRC_PREFIX).append(Base64Utility.encode(picture));
  return sb.toString();
}
