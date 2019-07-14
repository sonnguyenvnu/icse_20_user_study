public static void joinQQChat(Context context,String qqNumber){
  Intent intent=new Intent();
  intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber));
  try {
    context.startActivity(intent);
  }
 catch (  Exception e) {
    ToastUtil.showToastLong("????Q?????????~");
  }
}
