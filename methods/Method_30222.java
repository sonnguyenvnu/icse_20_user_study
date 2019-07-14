public void write(CollectableItem.Type itemType,long itemId,Context context){
  add(new UncollectItemWriter(itemType,itemId,this),context);
}
