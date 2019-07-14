private void initUCrop(Uri uri){
  SimpleDateFormat timeFormatter=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CHINA);
  long time=System.currentTimeMillis();
  String imageName=timeFormatter.format(new Date(time));
  Uri destinationUri=Uri.fromFile(new File(getCacheDir(),imageName + ".jpeg"));
  UCrop.Options options=new UCrop.Options();
  options.setAllowedGestures(UCropActivity.SCALE,UCropActivity.ROTATE,UCropActivity.ALL);
  options.setToolbarColor(ActivityCompat.getColor(this,R.color.colorPrimary));
  options.setStatusBarColor(ActivityCompat.getColor(this,R.color.colorPrimaryDark));
  options.setMaxScaleMultiplier(5);
  options.setImageToCropBoundsAnimDuration(666);
  UCrop.of(uri,destinationUri).withAspectRatio(1,1).withMaxResultSize(1000,1000).withOptions(options).start(this);
}
