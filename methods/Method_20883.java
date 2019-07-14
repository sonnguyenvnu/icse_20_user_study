public static Action1<String> showToast(final @NonNull Context context){
  return (message) -> showToast(context,message);
}
