public static String translate(String uri){
  return uri.startsWith("/play/") ? (PMS.get().getServer().getURL() + "/get/" + uri.substring(6).replace("%24","$")) : uri;
}
