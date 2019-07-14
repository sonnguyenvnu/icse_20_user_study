public static @NonNull ActivityResult androidPayErrorResult(){
  return activityResult().toBuilder().intent(new Intent().putExtra(WalletConstants.EXTRA_ERROR_CODE,1)).build();
}
