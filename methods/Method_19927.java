protected void onNewIntent(Intent intent){
  String action=intent.getAction();
  Uri data=intent.getData();
  if (Intent.ACTION_VIEW.equals(action) && data != null) {
    articleId=data.getLastPathSegment();
    TextView linkText=findViewById(R.id.link);
    linkText.setText(data.toString());
  }
}
