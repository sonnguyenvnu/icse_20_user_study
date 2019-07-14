public Document convert(String category,String text){
  String[] tokenArray=tokenizer.segment(text);
  return testingDataSet ? new Document(catalog.categoryId,lexicon.wordId,category,tokenArray) : new Document(catalog,lexicon,category,tokenArray);
}
