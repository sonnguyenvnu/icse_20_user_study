private CollectItemWriter findWriter(CollectableItem.Type itemType,long itemId){
  for (  CollectItemWriter writer : getWriters()) {
    if (writer.getItemType() == itemType && writer.getItemId() == itemId) {
      return writer;
    }
  }
  return null;
}
