@Override public void run(){
  final IFilesystemCache tileWriter=mMapView.getTileProvider().getTileWriter();
  if (tileWriter instanceof SqlTileWriter) {
    final int[] b=((SqlTileWriter)tileWriter).importFromFileCache(removeFromFileSystem);
    if (getActivity() != null) {
      getActivity().runOnUiThread(new Runnable(){
        @Override public void run(){
          Toast.makeText(getActivity(),"Cache Import success/failures/default/failres " + b[0] + "/" + b[1] + "/" + b[2] + "/" + b[3],Toast.LENGTH_LONG).show();
        }
      }
);
    }
  }
}
