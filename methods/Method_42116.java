private void handleDirectoryBase(ExifDirectoryBase d){
  if (d != null) {
    if (d.containsTag(ExifDirectoryBase.TAG_MAKE))     make=d.getString(ExifDirectoryBase.TAG_MAKE);
    if (d.containsTag(ExifDirectoryBase.TAG_MODEL))     model=d.getString(ExifDirectoryBase.TAG_MODEL);
    if (d.containsTag(ExifDirectoryBase.TAG_ISO_EQUIVALENT))     iso=d.getString(ExifDirectoryBase.TAG_ISO_EQUIVALENT);
    if (d.containsTag(ExifDirectoryBase.TAG_EXPOSURE_TIME) && d.getRational(ExifDirectoryBase.TAG_EXPOSURE_TIME) != null)     exposureTime=new DecimalFormat("0.000").format(d.getRational(ExifDirectoryBase.TAG_EXPOSURE_TIME));
    if (d.containsTag(ExifDirectoryBase.TAG_FNUMBER))     fNumber=d.getString(ExifDirectoryBase.TAG_FNUMBER);
    if (d.containsTag(ExifDirectoryBase.TAG_DATETIME_ORIGINAL))     dateOriginal=d.getDate(ExifDirectoryBase.TAG_DATETIME_ORIGINAL);
  }
}
