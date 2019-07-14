private void onRestoreInstanceState(Bundle savedInstanceState){
  compressedFile=new File(Uri.parse(savedInstanceState.getString(KEY_URI)).getPath());
  files=savedInstanceState.getParcelableArrayList(KEY_CACHE_FILES);
  isOpen=savedInstanceState.getBoolean(KEY_OPEN);
  elements=savedInstanceState.getParcelableArrayList(KEY_ELEMENTS);
  relativeDirectory=savedInstanceState.getString(KEY_PATH,"");
  decompressor=CompressedHelper.getCompressorInstance(getContext(),compressedFile);
  createViews(elements,relativeDirectory);
}
