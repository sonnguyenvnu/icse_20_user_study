public static Uri withoutQueryAndFragment(Uri uri){
  return uri.buildUpon().clearQuery().fragment(null).build();
}
