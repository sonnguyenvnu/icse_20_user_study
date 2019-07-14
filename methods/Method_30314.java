public static Intent makeIntent(CollectableItem item,Context context){
  return new Intent(context,ItemCollectionActivity.class).putExtra(EXTRA_ITEM,item);
}
