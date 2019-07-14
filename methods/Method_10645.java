public static void skipActivityForResult(Activity context,Class<?> goal,Bundle bundle,int requestCode){
  Intent intent=new Intent(context,goal);
  intent.putExtras(bundle);
  context.startActivityForResult(intent,requestCode);
}
