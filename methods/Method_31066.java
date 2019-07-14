public static Uri withAppendedId(Uri contentUri,long id){
  return appendId(contentUri.buildUpon(),id).build();
}
