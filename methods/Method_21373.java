private void startCheckoutActivity(final @NonNull Project project,final @NonNull Reward reward){
  final Context context=context();
  final Intent intent=new Intent(context,CheckoutActivity.class).putExtra(IntentKey.PROJECT,project).putExtra(IntentKey.TOOLBAR_TITLE,this.projectBackButtonString).putExtra(IntentKey.URL,project.rewardSelectedUrl(reward));
  context.startActivity(intent);
  transition(context,slideInFromRight());
}
