public SimpleDictionaryParser<V> setDict(DictionaryEntity dict){
  SimpleSingleDictParser toTextParser=new SimpleSingleDictParser();
  toTextParser.setDict(dict,DictionaryItemEntity::getValue,DictionaryItemEntity::getText,item -> toTextExpressions.get(item.getId()));
  SimpleSingleDictParser toValueParser=new SimpleSingleDictParser();
  toValueParser.setDict(dict,DictionaryItemEntity::getText,DictionaryItemEntity::getValue,item -> toValueExpressions.get(item.getId()));
  toValueParser.getTargetFormat().setSplitter(",");
  toValueParser.getTargetFormat().setChildStartChar(",");
  toValueParser.getTargetFormat().setChildEndChar("");
  toValueParser.getTargetFormat().setChildSplitter(",");
  this.setToTextParser(toTextParser);
  this.setToValueParser(toValueParser);
  return this;
}
