@Override protected void downloadImageInto(Uri uri,Target<File> target){
  if (mModel != null) {
    try {
      GlideModel glideModel=mModel.newInstance();
      glideModel.setBaseImageUrl(uri);
      mRequestManager.downloadOnly().load(glideModel).into(target);
    }
 catch (    InstantiationException e) {
      super.downloadImageInto(uri,target);
    }
catch (    IllegalAccessException e) {
      super.downloadImageInto(uri,target);
    }
  }
 else {
    super.downloadImageInto(uri,target);
  }
}
