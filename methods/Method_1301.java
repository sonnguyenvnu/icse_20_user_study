public static @Nullable ListenableActivity getListenableActivity(Context context){
  if (!(context instanceof ListenableActivity) && context instanceof ContextWrapper) {
    context=((ContextWrapper)context).getBaseContext();
  }
  if (context instanceof ListenableActivity) {
    return (ListenableActivity)context;
  }
  return null;
}
