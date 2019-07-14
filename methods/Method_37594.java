/** 
 * Returns basic information about the uploaded file.
 */
@Override public String toString(){
  return "FileUpload: uploaded=[" + isUploaded() + "] valid=[" + valid + "] field=[" + header.getFormFieldName() + "] name=[" + header.getFileName() + "] size=[" + size + ']';
}
