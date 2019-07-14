public String getClipboardText(Context context){
  StringBuilder builder=new StringBuilder();
  if (attachment != null) {
    builder.append(attachment.title).append('\n').append(attachment.href).append('\n').append(attachment.description);
  }
  if (!TextUtils.isEmpty(text)) {
    if (builder.length() > 0) {
      builder.append('\n');
    }
    builder.append(getTextWithEntities(context));
  }
  return builder.toString();
}
