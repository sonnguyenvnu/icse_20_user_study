private SimpleAdapter<Uri> initializeSimpleAdapter(final Config config){
  SimpleAdapter<Uri> simpleAdapter=null;
switch (config.dataSourceType) {
case Const.LOCAL_RESOURCE_URIS:
    simpleAdapter=LocalResourceSimpleAdapter.getEagerAdapter(getContext(),R.array.example_uris);
  break;
case Const.LOCAL_RESOURCE_WEBP_URIS:
simpleAdapter=LocalResourceSimpleAdapter.getEagerAdapter(getContext(),R.array.example_webp_uris);
break;
case Const.LOCAL_RESOURCE_PNG_URIS:
simpleAdapter=LocalResourceSimpleAdapter.getEagerAdapter(getContext(),R.array.example_png_uris);
break;
case Const.LOCAL_INTERNAL_PHOTO_URIS:
simpleAdapter=ContentProviderSimpleAdapter.getInternalPhotoSimpleAdapter(getActivity());
mDistinctUriCompatible=false;
break;
case Const.LOCAL_EXTERNAL_PHOTO_URIS:
simpleAdapter=getExternalPhotoSimpleAdapter();
mDistinctUriCompatible=false;
break;
}
return simpleAdapter;
}
