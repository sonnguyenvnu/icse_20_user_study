/** 
 * ????????JPEG?????
 * @param picPath JPEG??????
 * @param dLat    ??
 * @param dLon    ??
 */
public static void writeLatLonIntoJpeg(String picPath,double dLat,double dLon){
  File file=new File(picPath);
  if (file.exists()) {
    try {
      ExifInterface exif=new ExifInterface(picPath);
      String tagLat=exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
      String tagLon=exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
      if (tagLat == null && tagLon == null) {
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE,gpsInfoConvert(dLat));
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF,dLat > 0 ? "N" : "S");
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE,gpsInfoConvert(dLon));
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF,dLon > 0 ? "E" : "W");
        exif.saveAttributes();
      }
      exif.saveAttributes();
      Logger.d(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) + "\n" + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) + "\n" + exif.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD) + "\n" + exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) + "\n" + exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH));
    }
 catch (    Exception e) {
    }
  }
}
