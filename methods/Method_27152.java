private static void openChooser(@NonNull Context context,@NonNull Uri url,boolean fromCustomTab){
  Intent i=new Intent(Intent.ACTION_VIEW,url);
  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  Intent finalIntent=chooserIntent(context,i,url);
  if (finalIntent != null) {
    try {
      context.startActivity(finalIntent);
    }
 catch (    ActivityNotFoundException ignored) {
    }
  }
 else {
    if (!fromCustomTab) {
      Activity activity=ActivityHelper.getActivity(context);
      if (activity == null) {
        try {
          context.startActivity(i);
        }
 catch (        ActivityNotFoundException ignored) {
        }
        return;
      }
      startCustomTab(activity,url);
    }
 else {
      try {
        context.startActivity(i);
      }
 catch (      ActivityNotFoundException ignored) {
      }
    }
  }
}
