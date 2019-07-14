private static String truncateReleaseDate(String releaseDate){
  if (TextUtils.isEmpty(releaseDate) || releaseDate.length() < 10) {
    return releaseDate;
  }
  return releaseDate.substring(0,10);
}
