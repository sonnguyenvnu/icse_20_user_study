@Nullable public final Boolean handleRequestedPermission(@NonNull String permission){
  for (  RouterTransaction transaction : backstack) {
    if (transaction.controller.didRequestPermission(permission)) {
      return transaction.controller.shouldShowRequestPermissionRationale(permission);
    }
  }
  return null;
}
