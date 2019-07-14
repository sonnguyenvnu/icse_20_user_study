public static void startReveal(@NonNull Activity activity,Intent intent,@NonNull View sharedElement,int requestCode){
  if (!PrefGetter.isAppAnimationDisabled()) {
    ActivityOptionsCompat options=ActivityOptionsCompat.makeClipRevealAnimation(sharedElement,sharedElement.getWidth() / 2,sharedElement.getHeight() / 2,sharedElement.getWidth(),sharedElement.getHeight());
    activity.startActivityForResult(intent,requestCode,options.toBundle());
  }
 else {
    activity.startActivityForResult(intent,requestCode);
  }
}
