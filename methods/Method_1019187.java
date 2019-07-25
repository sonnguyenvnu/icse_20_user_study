/** 
 * get RxMarkdown object
 * @param content the markdown content
 * @param context {@link Context}
 * @return RxMarkdown object
 */
public static RxMarkdown with(String content,Context context){
  return new RxMarkdown(content,context);
}
