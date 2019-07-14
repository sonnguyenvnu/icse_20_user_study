public void write(CollectableItem item,Context context){
  add(new UncollectItemWriter(item,this),context);
}
