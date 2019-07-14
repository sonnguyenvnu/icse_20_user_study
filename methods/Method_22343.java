/** 
 * Creates a temporary file with the given content and name, to be used as an email attachment
 * @param context a context
 * @param name    the name
 * @param content the content
 * @return a content uri for the file
 */
@Nullable protected Uri createAttachmentFromString(@NonNull Context context,@NonNull String name,@NonNull String content){
  final File cache=new File(context.getCacheDir(),name);
  try {
    IOUtils.writeStringToFile(cache,content);
    return AcraContentProvider.getUriForFile(context,cache);
  }
 catch (  IOException ignored) {
  }
  return null;
}
