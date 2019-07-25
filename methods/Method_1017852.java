/** 
 * Ask for the permission. Which permission? Anything you register on your manifest that needs it. It is safe to call this every time without querying `shouldAsk`. In case you call `ask` without any permission, the method returns.
 */
public void ask(){
  final FragmentActivity activity=activityReference.get();
  if (activity == null || activity.isFinishing()) {
    return;
  }
  final List<String> permissions=findNeededPermissions(activity);
  if (permissions.isEmpty() || Build.VERSION.SDK_INT < Build.VERSION_CODES.M || arePermissionsAlreadyAccepted(activity,permissions)) {
    onAllAccepted(permissions);
  }
 else {
    final PermissionFragment oldFragment=(PermissionFragment)activity.getSupportFragmentManager().findFragmentByTag(TAG);
    if (oldFragment != null) {
      oldFragment.setListener(listener);
    }
 else {
      final PermissionFragment newFragment=PermissionFragment.newInstance(permissions);
      newFragment.setListener(listener);
      activity.runOnUiThread(new Runnable(){
        @Override public void run(){
          activity.getSupportFragmentManager().beginTransaction().add(newFragment,TAG).commitNowAllowingStateLoss();
        }
      }
);
    }
  }
}
