private void updateUI(FirebaseUser user){
  if (user != null) {
    findViewById(R.id.layoutSignin).setVisibility(View.GONE);
    findViewById(R.id.layoutStorage).setVisibility(View.VISIBLE);
  }
 else {
    findViewById(R.id.layoutSignin).setVisibility(View.VISIBLE);
    findViewById(R.id.layoutStorage).setVisibility(View.GONE);
  }
  if (mDownloadUrl != null) {
    ((TextView)findViewById(R.id.pictureDownloadUri)).setText(mDownloadUrl.toString());
    findViewById(R.id.layoutDownload).setVisibility(View.VISIBLE);
  }
 else {
    ((TextView)findViewById(R.id.pictureDownloadUri)).setText(null);
    findViewById(R.id.layoutDownload).setVisibility(View.GONE);
  }
}
