@Override public void onMediaClick(Album album,ArrayList<Media> media,int position){
  if (!pickMode) {
    Intent intent=new Intent(getApplicationContext(),SingleMediaActivity.class);
    intent.putExtra(SingleMediaActivity.EXTRA_ARGS_ALBUM,album);
    try {
      intent.setAction(SingleMediaActivity.ACTION_OPEN_ALBUM);
      intent.putExtra(SingleMediaActivity.EXTRA_ARGS_MEDIA,media);
      intent.putExtra(SingleMediaActivity.EXTRA_ARGS_POSITION,position);
      startActivity(intent);
    }
 catch (    Exception e) {
      intent.setAction(SingleMediaActivity.ACTION_OPEN_ALBUM_LAZY);
      intent.putExtra(SingleMediaActivity.EXTRA_ARGS_MEDIA,media.get(position));
      startActivity(intent);
    }
  }
 else {
    Media m=media.get(position);
    Uri uri=LegacyCompatFileProvider.getUri(getApplicationContext(),m.getFile());
    Intent res=new Intent();
    res.setData(uri);
    res.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    setResult(RESULT_OK,res);
    finish();
  }
}
