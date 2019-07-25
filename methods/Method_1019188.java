/** 
 * get RxMarkdown object. Live preview in  {@link MarkdownEditText}.
 * @param MarkdownEditText RxMDEditText
 * @return RxMarkdown object
 */
public static RxMarkdown live(MarkdownEditText MarkdownEditText){
  return new RxMarkdown(MarkdownEditText);
}
