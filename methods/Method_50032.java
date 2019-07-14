/** 
 * Ends a conversion session of a file.
 * @param filename The filename of the converted file.
 * @return STATUS_SUCCESS if execution is ok.STATUS_FILE_ERROR in case converted file can not be accessed. STATUS_NOT_ACCEPTABLE if a problem occurs when accessing drm framework. STATUS_UNKNOWN_ERROR if a general error occurred.
 */
public int close(String filename){
  DrmConvertedStatus convertedStatus=null;
  int result=STATUS_UNKNOWN_ERROR;
  if (mDrmClient != null && mConvertSessionId >= 0) {
    try {
      convertedStatus=mDrmClient.closeConvertSession(mConvertSessionId);
      if (convertedStatus == null || convertedStatus.statusCode != DrmConvertedStatus.STATUS_OK || convertedStatus.convertedData == null) {
        result=STATUS_NOT_ACCEPTABLE;
      }
 else {
        RandomAccessFile rndAccessFile=null;
        try {
          rndAccessFile=new RandomAccessFile(filename,"rw");
          rndAccessFile.seek(convertedStatus.offset);
          rndAccessFile.write(convertedStatus.convertedData);
          result=STATUS_SUCCESS;
        }
 catch (        FileNotFoundException e) {
          result=STATUS_FILE_ERROR;
          Timber.w(e,"File: " + filename + " could not be found.");
        }
catch (        IOException e) {
          result=STATUS_FILE_ERROR;
          Timber.w(e,"Could not access File: " + filename + " .");
        }
catch (        IllegalArgumentException e) {
          result=STATUS_FILE_ERROR;
          Timber.w(e,"Could not open file in mode: rw");
        }
catch (        SecurityException e) {
          Timber.w("Access to File: " + filename + " was denied denied by SecurityManager.",e);
        }
 finally {
          if (rndAccessFile != null) {
            try {
              rndAccessFile.close();
            }
 catch (            IOException e) {
              result=STATUS_FILE_ERROR;
              Timber.w("Failed to close File:" + filename + ".",e);
            }
          }
        }
      }
    }
 catch (    IllegalStateException e) {
      Timber.w("Could not close convertsession. Convertsession: " + mConvertSessionId,e);
    }
  }
  return result;
}
