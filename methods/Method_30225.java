private UncollectItemWriter findWriter(CollectableItem.Type itemType,long itemId){
  for (  UncollectItemWriter writer : getWriters()) {
    if (writer.getItemType() == itemType && writer.getItemId() == itemId) {
      return writer;
    }
  }
  return null;
}
