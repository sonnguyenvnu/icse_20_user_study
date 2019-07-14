public static void startReveal(@NonNull Activity activity,Intent intent,@NonNull View sharedElement){
  if (!PrefGetter.isAppAnimationDisabled()) {
    ActivityOptionsCompat options=ActivityOptionsCompat.makeClipRevealAnimation(sharedElement,sharedElement.getWidth() / 2,sharedElement.getHeight() / 2,sharedElement.getWidth(),sharedElement.getHeight());
    activity.startActivity(intent,options.toBundle());
  }
 else {
    activity.startActivity(intent);
  }
}
