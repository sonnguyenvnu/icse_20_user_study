/** 
 * Saves new image file with current Exif information. It is quite expensive operation, so it is recomended to call it only at the end of work.
 * @param _name name of the new file
 */
public void save(String _name){
  ValueNumber val=new ValueNumber(FORMAT_UNSIGNED_LONG,0);
  ifd0.put(TAG_EXIF_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,0);
  ifd0.put(TAG_GPS_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,0);
  ifdExif.put(TAG_INTEROPERABILITY_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,0);
  ifd1.put(TAG_JPEG_INTERCHANGE_FORMAT,val);
  int startOfIfd0=TIFFHeader.length;
  int startOfIfdExif=startOfIfd0 + requiredSpace(ifd0);
  int startOfIfdIOper=startOfIfdExif + requiredSpace(ifdExif);
  int startOfIfdGps=startOfIfdIOper + requiredSpace(ifdIOper);
  int startOfIfd1=startOfIfdGps + requiredSpace(ifdGps);
  int startOfThumbnail=startOfIfd1 + requiredSpace(ifd1);
  int reqSize=startOfThumbnail + origThumbnailLength;
  if (origThumbnailOffset == -1) {
    reqSize=startOfIfd1;
  }
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,startOfIfdExif);
  ifd0.put(TAG_EXIF_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,startOfIfdGps);
  ifd0.put(TAG_GPS_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,startOfIfdIOper);
  ifdExif.put(TAG_INTEROPERABILITY_POINTER,val);
  val=new ValueNumber(FORMAT_UNSIGNED_LONG,startOfThumbnail);
  ifd1.put(TAG_JPEG_INTERCHANGE_FORMAT,val);
  byte[] resultExif=new byte[reqSize];
  byte[] exifHeader=new byte[]{(byte)0xFF,(byte)0xE1,0,0,(byte)0x45,(byte)0x78,(byte)0x69,(byte)0x66,0,0};
  exifHeader[2]=(byte)(((reqSize + 8) & 0xFF00) >> 8);
  exifHeader[3]=(byte)((reqSize + 8) & 0xFF);
  byte[] tiffHeader=new byte[]{0x49,0x49,0x2A,0x00,0x08,0x00,0x00,0x00};
  System.arraycopy(tiffHeader,0,resultExif,0,tiffHeader.length);
  writeIfd(resultExif,ifd0,startOfIfd0,origThumbnailOffset == -1 ? 0 : startOfIfd1);
  writeIfd(resultExif,ifdExif,startOfIfdExif,0);
  writeIfd(resultExif,ifdIOper,startOfIfdIOper,0);
  writeIfd(resultExif,ifdGps,startOfIfdGps,0);
  if (origThumbnailOffset != -1) {
    writeIfd(resultExif,ifd1,startOfIfd1,0);
    System.arraycopy(origEXIFdata,origThumbnailOffset,resultExif,startOfThumbnail,origThumbnailLength);
  }
  FileOutputStream fos=null;
  FileInputStream fis=null;
  try {
    fos=new FileOutputStream(_name);
    fis=new FileInputStream(sourceFile);
    fos.write((byte)0xFF);
    fos.write((byte)0xD8);
    fos.write(exifHeader);
    fos.write(resultExif);
    int skipped=0;
    int imageOffset=origAPP1MarkerOffset + APP1Marker.length + LENGTH_EXIF_SIZE_DECL + EXIFHeader.length + origEXIFdata.length;
    while (skipped < imageOffset) {
      int skip=(int)fis.skip(imageOffset - skipped);
      if (skip < 0) {
        fos.close();
        throw (new IOException());
      }
 else {
        skipped+=skip;
      }
    }
    byte[] buffer=new byte[10240];
    int len;
    while ((len=fis.read(buffer)) > 0) {
      fos.write(buffer,0,len);
    }
    fis.close();
    fos.close();
  }
 catch (  FileNotFoundException ex) {
    Logger.getLogger(ExifDriver.class.getName()).log(Level.SEVERE,null,ex);
  }
catch (  IOException ex) {
    Logger.getLogger(ExifDriver.class.getName()).log(Level.SEVERE,null,ex);
  }
 finally {
    try {
      fis.close();
      fos.close();
    }
 catch (    IOException ex) {
      Logger.getLogger(ExifDriver.class.getName()).log(Level.SEVERE,null,ex);
    }
  }
}
