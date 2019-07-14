private void startActivity(String url){
  try {
    Intent intent1=new Intent();
    intent1.setAction("android.intent.action.VIEW");
    Uri uri=Uri.parse(url);
    intent1.setData(uri);
    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mActivity.startActivity(intent1);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
