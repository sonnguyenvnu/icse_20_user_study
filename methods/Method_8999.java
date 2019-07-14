public void setSectionsType(int type){
  sectionsType=type;
  if (sectionsType == 1) {
    headers=new ArrayList<>();
    headersCache=new ArrayList<>();
  }
}
