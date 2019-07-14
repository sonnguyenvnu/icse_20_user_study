public List<String> getPageCountStrings(){
  return Functional.map(pageCounts,pageCount -> pageCount + "?");
}
