/** 
 * Save data of the part into storage. The source data may be given by a byte[] or a Uri. If it's a byte[], directly save it into storage, otherwise load source data from the dataUri and then save it. If the data is an image, we may scale down it according to user preference.
 * @param part           The PDU part which contains data to be saved.
 * @param uri            The URI of the part.
 * @param contentType    The MIME type of the part.
 * @param preOpenedFiles if not null, a map of preopened InputStreams for the parts.
 * @throws MmsException Cannot find source data or error occurredwhile saving the data.
 */
private void persistData(PduPart part,Uri uri,String contentType,HashMap<Uri,InputStream> preOpenedFiles) throws MmsException {
  OutputStream os=null;
  InputStream is=null;
  DrmConvertSession drmConvertSession=null;
  Uri dataUri=null;
  String path=null;
  try {
    byte[] data=part.getData();
    if (ContentType.TEXT_PLAIN.equals(contentType) || ContentType.APP_SMIL.equals(contentType) || ContentType.TEXT_HTML.equals(contentType)) {
      ContentValues cv=new ContentValues();
      if (data == null) {
        data=new String("").getBytes(CharacterSets.DEFAULT_CHARSET_NAME);
      }
      String dataText=new EncodedStringValue(data).getString();
      cv.put(Part.TEXT,dataText);
      if (mContentResolver.update(uri,cv,null,null) != 1) {
        if (data.length > MAX_TEXT_BODY_SIZE) {
          ContentValues cv2=new ContentValues();
          cv2.put(Part.TEXT,cutString(dataText,MAX_TEXT_BODY_SIZE));
          if (mContentResolver.update(uri,cv2,null,null) != 1) {
            throw new MmsException("unable to update " + uri.toString());
          }
        }
 else {
          throw new MmsException("unable to update " + uri.toString());
        }
      }
    }
 else {
      boolean isDrm=DownloadDrmHelper.isDrmConvertNeeded(contentType);
      if (isDrm) {
        if (uri != null) {
          try {
            path=convertUriToPath(mContext,uri);
            if (LOCAL_LOGV) {
              Timber.v("drm uri: " + uri + " path: " + path);
            }
            File f=new File(path);
            long len=f.length();
            if (LOCAL_LOGV) {
              Timber.v("drm path: " + path + " len: " + len);
            }
            if (len > 0) {
              return;
            }
          }
 catch (          Exception e) {
            Timber.e(e,"Can't get file info for: " + part.getDataUri());
          }
        }
        drmConvertSession=DrmConvertSession.open(mContext,contentType);
        if (drmConvertSession == null) {
          throw new MmsException("Mimetype " + contentType + " can not be converted.");
        }
      }
      os=mContentResolver.openOutputStream(uri);
      if (data == null) {
        dataUri=part.getDataUri();
        if ((dataUri == null) || (dataUri == uri)) {
          Timber.w("Can't find data for this part.");
          return;
        }
        if (preOpenedFiles != null && preOpenedFiles.containsKey(dataUri)) {
          is=preOpenedFiles.get(dataUri);
        }
        if (is == null) {
          is=mContentResolver.openInputStream(dataUri);
        }
        if (LOCAL_LOGV) {
          Timber.v("Saving data to: " + uri);
        }
        byte[] buffer=new byte[8192];
        for (int len=0; (len=is.read(buffer)) != -1; ) {
          if (!isDrm) {
            os.write(buffer,0,len);
          }
 else {
            byte[] convertedData=drmConvertSession.convert(buffer,len);
            if (convertedData != null) {
              os.write(convertedData,0,convertedData.length);
            }
 else {
              throw new MmsException("Error converting drm data.");
            }
          }
        }
      }
 else {
        if (LOCAL_LOGV) {
          Timber.v("Saving data to: " + uri);
        }
        if (!isDrm) {
          os.write(data);
        }
 else {
          dataUri=uri;
          byte[] convertedData=drmConvertSession.convert(data,data.length);
          if (convertedData != null) {
            os.write(convertedData,0,convertedData.length);
          }
 else {
            throw new MmsException("Error converting drm data.");
          }
        }
      }
    }
  }
 catch (  FileNotFoundException e) {
    Timber.e(e,"Failed to open Input/Output stream.");
    throw new MmsException(e);
  }
catch (  IOException e) {
    Timber.e(e,"Failed to read/write data.");
    throw new MmsException(e);
  }
 finally {
    if (os != null) {
      try {
        os.close();
      }
 catch (      IOException e) {
        Timber.e(e,"IOException while closing: " + os);
      }
    }
    if (is != null) {
      try {
        is.close();
      }
 catch (      IOException e) {
        Timber.e(e,"IOException while closing: " + is);
      }
    }
    if (drmConvertSession != null) {
      drmConvertSession.close(path);
      File f=new File(path);
      ContentValues values=new ContentValues(0);
      SqliteWrapper.update(mContext,mContentResolver,Uri.parse("content://mms/resetFilePerm/" + f.getName()),values,null,null);
    }
  }
}
