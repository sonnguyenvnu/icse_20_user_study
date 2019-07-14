public static void skipActivityForResult(Activity context,Class<?> goal,int requestCode){
  Intent intent=new Intent(context,goal);
  context.startActivityForResult(intent,requestCode);
}
