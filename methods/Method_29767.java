public static void addAccount(Activity activity,Intent onAddedIntent){
  activity.startActivity(AddAccountActivity.makeIntent(onAddedIntent,activity));
}
