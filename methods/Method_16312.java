public void setDict(DictionaryEntity dict,Function<DictionaryItemEntity,String> keyGetter,Function<DictionaryItemEntity,String> valueGetter,Function<DictionaryItemEntity,String> expressionGetter){
  dict.getItems().forEach(item -> addMapping(item,keyGetter,valueGetter,expressionGetter));
}
