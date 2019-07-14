public static Intent makeIntent(Intent onSelectedIntent,Context context){
  return new Intent(context,SelectAccountActivity.class).putExtra(SelectAccountActivity.EXTRA_ON_SELECTED_INTENT,onSelectedIntent);
}
