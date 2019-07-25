/** 
 * Substitutes each  {@code %s} in {@code template} with an argument. These are matched byposition: the first  {@code %s} gets {@code args[0]}, etc. If there are more arguments than placeholders, the unmatched arguments will be appended to the end of the formatted message in square braces. <p>Copied from  {@code Preconditions.format(String, Object...)} from Guava
 * @param template a non-null string containing 0 or more {@code %s} placeholders.
 * @param args the arguments to be substituted into the message template. Arguments are convertedto strings using  {@link String#valueOf(Object)}. Arguments can be null.
 */
private static String format(String template,@javax.annotation.Nullable Object... args){
  if (args == null) {
    return template;
  }
  StringBuilder builder=new StringBuilder(template.length() + 16 * args.length);
  int templateStart=0;
  int i=0;
  while (i < args.length) {
    int placeholderStart=template.indexOf("%s",templateStart);
    if (placeholderStart == -1) {
      break;
    }
    builder.append(template,templateStart,placeholderStart);
    builder.append(args[i++]);
    templateStart=placeholderStart + 2;
  }
  builder.append(template,templateStart,template.length());
  if (i < args.length) {
    builder.append(" [");
    builder.append(args[i++]);
    while (i < args.length) {
      builder.append(", ");
      builder.append(args[i++]);
    }
    builder.append(']');
  }
  return builder.toString();
}
