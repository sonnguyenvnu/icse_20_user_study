@Keep public static Bundle getBundleForCall(Uri uri){
  Bundle bundle=new Bundle();
  bundle.putString(RemoteContentProvider.KEY_WRAPPER_URI,uri.toString());
  return bundle;
}
