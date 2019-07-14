public static Intent makeIntent(User user,Context context){
  return new Intent(context,ProfileActivity.class).putExtra(EXTRA_USER_INFO,user);
}
