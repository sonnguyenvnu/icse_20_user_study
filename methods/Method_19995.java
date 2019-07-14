private void validateAppCode(){
  String uriPrefix=getString(R.string.dynamic_links_uri_prefix);
  if (uriPrefix.contains("YOUR_APP")) {
    new AlertDialog.Builder(this).setTitle("Invalid Configuration").setMessage("Please set your Dynamic Links domain in app/build.gradle").setPositiveButton(android.R.string.ok,null).create().show();
  }
}
