public static Intent makeIntent(SimpleUser simpleUser,Context context){
  return new Intent(context,ProfileActivity.class).putExtra(EXTRA_SIMPLE_USER,simpleUser);
}
