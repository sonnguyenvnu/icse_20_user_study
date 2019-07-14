public static void launchUri(@NonNull Context context,@NonNull Uri data,boolean showRepoBtn,boolean newDocument){
  Logger.e(data);
  Intent intent=convert(context,data,showRepoBtn);
  if (intent != null) {
    intent.putExtra(BundleConstant.SCHEME_URL,data.toString());
    if (newDocument) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
    }
    if (context instanceof Service || context instanceof Application) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }
 else {
    Activity activity=ActivityHelper.getActivity(context);
    if (activity != null) {
      ActivityHelper.startCustomTab(activity,data);
    }
 else {
      ActivityHelper.openChooser(context,data);
    }
  }
}
