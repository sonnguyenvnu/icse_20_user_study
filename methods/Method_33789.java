@TargetApi(Build.VERSION_CODES.KITKAT) private static void clearPreviousSetting(Activity activity){
  ViewGroup decorView=(ViewGroup)activity.getWindow().getDecorView();
  int count=decorView.getChildCount();
  if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
    decorView.removeViewAt(count - 1);
    ViewGroup rootView=(ViewGroup)((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
    rootView.setPadding(0,0,0,0);
  }
}
