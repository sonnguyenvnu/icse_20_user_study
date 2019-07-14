@Override protected void onProgressUpdate(Integer... values){
  super.onProgressUpdate(values);
  dbViewerFragment.loadingText.setText(values[0] + " records loaded");
}
