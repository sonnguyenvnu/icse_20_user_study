/** 
 * Called when the end of a paragraph is encountered. Adds a newline if there are one or more non-space characters since the previous newline.
 * @param builder The builder.
 */
static void endParagraph(SpannableStringBuilder builder){
  int position=builder.length() - 1;
  while (position >= 0 && builder.charAt(position) == ' ') {
    position--;
  }
  if (position >= 0 && builder.charAt(position) != '\n') {
    builder.append('\n');
  }
}
