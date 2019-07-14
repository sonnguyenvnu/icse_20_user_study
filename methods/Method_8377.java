public static Toast showSimpleToast(BaseFragment baseFragment,final String text){
  if (text == null) {
    return null;
  }
  Context context;
  if (baseFragment != null && baseFragment.getParentActivity() != null) {
    context=baseFragment.getParentActivity();
  }
 else {
    context=ApplicationLoader.applicationContext;
  }
  Toast toast=Toast.makeText(context,text,Toast.LENGTH_LONG);
  toast.show();
  return toast;
}
