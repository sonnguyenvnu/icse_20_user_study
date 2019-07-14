public static Intent makeIntent(CollectableItem item,Context context){
  return new Intent(context,ItemIntroductionActivity.class).putExtra(EXTRA_ITEM,item);
}
