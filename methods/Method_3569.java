static void changeDelimiterPOS(List<Vertex> linkedArray){
  for (  Vertex vertex : linkedArray) {
    if (vertex.realWord.equals("??") || vertex.realWord.equals("—") || vertex.realWord.equals("-")) {
      vertex.confirmNature(Nature.w);
    }
  }
}
