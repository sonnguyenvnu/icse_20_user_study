private Activity getActivity(){
  Context context=getContext();
  while (context instanceof ContextWrapper) {
    if (context instanceof Activity) {
      return (Activity)context;
    }
    context=((ContextWrapper)context).getBaseContext();
  }
  return null;
}
