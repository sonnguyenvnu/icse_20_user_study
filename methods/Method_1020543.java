public static void init(Context context){
  if (sToast == null) {
    sToast=Toast.makeText(context,"",Toast.LENGTH_SHORT);
  }
}
