private void shareDeepLink(String deepLink){
  Intent intent=new Intent(Intent.ACTION_SEND);
  intent.setType("text/plain");
  intent.putExtra(Intent.EXTRA_SUBJECT,"Firebase Deep Link");
  intent.putExtra(Intent.EXTRA_TEXT,deepLink);
  startActivity(intent);
}
