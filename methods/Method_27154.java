@SafeVarargs public static void start(@NonNull Activity activity,Class cl,Pair<View,String>... sharedElements){
  Intent intent=new Intent(activity,cl);
  ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(activity,sharedElements);
  activity.startActivity(intent,options.toBundle());
}
