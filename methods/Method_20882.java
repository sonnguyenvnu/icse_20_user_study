/** 
 * Show a toast with default bottom gravity to the user.
 */
@SuppressLint("InflateParams") public static void showToast(final @NonNull Context context,final @NonNull String message){
  final LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  final View view=inflater.inflate(R.layout.toast,null);
  final TextView text=(TextView)view.findViewById(R.id.toast_text_view);
  text.setText(message);
  final Toast toast=new Toast(context);
  toast.setView(view);
  toast.show();
}
