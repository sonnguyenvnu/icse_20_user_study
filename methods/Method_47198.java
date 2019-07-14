public static @DrawableRes int loadMimeIcon(String path,boolean isDirectory){
  if (path.equals(".."))   return R.drawable.ic_arrow_left_white_24dp;
  if (CompressedHelper.isFileExtractable(path))   return R.drawable.ic_compressed_white_24dp;
  int type=getTypeOfFile(path,isDirectory);
switch (type) {
case APK:
    return R.drawable.ic_doc_apk_white;
case AUDIO:
  return R.drawable.ic_doc_audio_am;
case IMAGE:
return R.drawable.ic_doc_image;
case TEXT:
return R.drawable.ic_doc_text_am;
case VIDEO:
return R.drawable.ic_doc_video_am;
case PDF:
return R.drawable.ic_doc_pdf;
case CERTIFICATE:
return R.drawable.ic_doc_certificate;
case CODE:
return R.drawable.ic_doc_codes;
case FONT:
return R.drawable.ic_doc_font;
case ENCRYPTED:
return R.drawable.ic_folder_lock_white_36dp;
default :
if (isDirectory) return R.drawable.ic_grid_folder_new;
 else {
return R.drawable.ic_doc_generic_am;
}
}
}
