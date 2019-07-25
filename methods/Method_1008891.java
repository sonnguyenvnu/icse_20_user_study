private CTBookmark find(List<CTBookmark> starts,BigInteger id){
  for (  CTBookmark bm : starts) {
    if (bm.getId() == id) {
      return bm;
    }
  }
  return null;
}
