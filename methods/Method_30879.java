private boolean isDoubanUrl(String url){
  return DOUBAN_HOST_PATTERN.matcher(Uri.parse(url).getHost()).matches();
}
