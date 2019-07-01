/** 
 * Sets the file type to be transferred.  This should be one of <code> FTP.ASCII_FILE_TYPE </code>, <code> FTP.BINARY_FILE_TYPE</code>, etc.  The file type only needs to be set when you want to change the type.  After changing it, the new type stays in effect until you change it again.  The default file type is <code> FTP.ASCII_FILE_TYPE </code> if this method is never called. <br> The server default is supposed to be ASCII (see RFC 959), however many ftp servers default to BINARY. <b>To ensure correct operation with all servers, always specify the appropriate file type after connecting to the server.</b> <br> <p> <b>N.B.</b> currently calling any connect method will reset the type to FTP.ASCII_FILE_TYPE.
 * @param fileType The <code> _FILE_TYPE </code> constant indicating thetype of file.
 * @return True if successfully completed, false if not.
 * @throws FTPConnectionClosedException If the FTP server prematurely closes the connection as a result of the client being idle or some other reason causing the server to send FTP reply code 421.  This exception may be caught either as an IOException or independently as itself.
 * @throws IOException  If an I/O error occurs while either sending acommand to the server or receiving a reply from the server.
 */
public boolean _XXXXX_(int fileType) throws IOException {
  if (FTPReply.isPositiveCompletion(type(fileType))) {
    __fileType=fileType;
    __fileFormat=FTP.NON_PRINT_TEXT_FORMAT;
    return true;
  }
  return false;
}