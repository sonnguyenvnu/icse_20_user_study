private void setSourceAdapter(int index){
  FLog.v(TAG,"onImageSourceSelect: %d",index);
  mCurrentSourceAdapterIndex=index;
switch (index) {
case NETWORK_INDEX:
    mUrlsLocal=false;
  break;
case LOCAL_INDEX:
mUrlsLocal=true;
break;
default :
resetAdapter();
mImageUrls.clear();
return;
}
loadUrls();
setLoaderAdapter(mCurrentLoaderAdapterIndex);
}
