protected String normalize(String string){
  return Normalizer.normalize(string,Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
}
