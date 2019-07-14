/** 
 * ????
 */
private void radioNext(MediaBean mediaBean){
  Logger.i("isCrop :" + mConfiguration.isCrop());
  if (!mConfiguration.isCrop()) {
    setPostMediaBean(mediaBean);
    getActivity().finish();
  }
 else {
    setPostMediaBean(mediaBean);
    String originalPath=mediaBean.getOriginalPath();
    File file=new File(originalPath);
    Random random=new Random();
    String outName=String.format(IMAGE_STORE_FILE_NAME,SimpleDateUtils.getNowTime() + "_" + random.nextInt(1024));
    Logger.i("--->isCrop:" + mImageStoreCropDir);
    Logger.i("--->mediaBean.getOriginalPath():" + mediaBean.getOriginalPath());
    mCropPath=new File(mImageStoreCropDir,outName);
    Uri outUri=Uri.fromFile(mCropPath);
    if (!mImageStoreCropDir.exists()) {
      mImageStoreCropDir.mkdirs();
    }
    if (!file.exists()) {
      file.mkdirs();
    }
    Uri inputUri=Uri.fromFile(new File(mediaBean.getOriginalPath()));
    Intent intent=new Intent(getContext(),UCropActivity.class);
    Bundle bundle=new Bundle();
    bundle.putParcelable(UCrop.EXTRA_OUTPUT_URI,outUri);
    bundle.putParcelable(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS,mediaBean);
    bundle.putInt(UCrop.Options.EXTRA_STATUS_BAR_COLOR,uCropStatusColor);
    bundle.putInt(UCrop.Options.EXTRA_TOOL_BAR_COLOR,uCropToolbarColor);
    bundle.putString(UCrop.Options.EXTRA_UCROP_TITLE_TEXT_TOOLBAR,uCropTitle);
    bundle.putInt(UCrop.Options.EXTRA_UCROP_COLOR_WIDGET_ACTIVE,uCropActivityWidgetColor);
    bundle.putInt(UCrop.Options.EXTRA_UCROP_WIDGET_COLOR_TOOLBAR,uCropToolbarWidgetColor);
    bundle.putBoolean(UCrop.Options.EXTRA_HIDE_BOTTOM_CONTROLS,mConfiguration.isHideBottomControls());
    bundle.putIntArray(UCrop.Options.EXTRA_ALLOWED_GESTURES,mConfiguration.getAllowedGestures());
    bundle.putInt(UCrop.Options.EXTRA_COMPRESSION_QUALITY,mConfiguration.getCompressionQuality());
    bundle.putInt(UCrop.Options.EXTRA_MAX_BITMAP_SIZE,mConfiguration.getMaxBitmapSize());
    bundle.putFloat(UCrop.Options.EXTRA_MAX_SCALE_MULTIPLIER,mConfiguration.getMaxScaleMultiplier());
    bundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_X,mConfiguration.getAspectRatioX());
    bundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_Y,mConfiguration.getAspectRatioY());
    bundle.putInt(UCrop.EXTRA_MAX_SIZE_X,mConfiguration.getMaxResultWidth());
    bundle.putInt(UCrop.EXTRA_MAX_SIZE_Y,mConfiguration.getMaxResultHeight());
    bundle.putInt(UCrop.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT,mConfiguration.getSelectedByDefault());
    bundle.putBoolean(UCrop.Options.EXTRA_FREE_STYLE_CROP,mConfiguration.isFreestyleCropEnabled());
    bundle.putParcelable(UCrop.EXTRA_INPUT_URI,inputUri);
    int bk=FileUtils.existImageDir(inputUri.getPath());
    Logger.i("--->" + inputUri.getPath());
    Logger.i("--->" + outUri.getPath());
    ArrayList<AspectRatio> aspectRatioList=new ArrayList<>();
    AspectRatio[] aspectRatios=mConfiguration.getAspectRatio();
    if (aspectRatios != null) {
      for (int i=0; i < aspectRatios.length; i++) {
        aspectRatioList.add(i,aspectRatios[i]);
        Logger.i("?????=>" + aspectRatioList.get(i).getAspectRatioX() + " " + aspectRatioList.get(i).getAspectRatioY());
      }
    }
    bundle.putParcelableArrayList(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS,aspectRatioList);
    intent.putExtras(bundle);
    if (bk != -1) {
      startActivityForResult(intent,CROP_IMAGE_REQUEST_CODE);
    }
 else {
      Logger.w("??????");
    }
  }
}
