public static Intent makeIntent(Intent onAddedIntent,Context context){
  return new Intent(context,AddAccountActivity.class).putExtra(EXTRA_ON_ADDED_INTENT,onAddedIntent);
}
