private String normalize(String string){
  String decomposed=Normalizer.normalize(string,Normalizer.Form.NFD);
  String noDiacriticals=removeDiacriticalMarks(decomposed);
  return Normalizer.normalize(noDiacriticals,Normalizer.Form.NFC);
}
