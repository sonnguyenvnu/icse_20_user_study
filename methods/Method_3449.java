public static String[][] reshapeNER(List<String[]> ner){
  String[] wordArray=new String[ner.size()];
  String[] posArray=new String[ner.size()];
  String[] nerArray=new String[ner.size()];
  reshapeNER(ner,wordArray,posArray,nerArray);
  return new String[][]{wordArray,posArray,nerArray};
}
