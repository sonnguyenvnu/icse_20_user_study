/** 
 * Saves the configuration to disk. All errors are clobbered.
 * @return true if it was successful
 */
public boolean save(){
  File parent=file.getParentFile();
  if (parent != null) {
    parent.mkdirs();
  }
  try (OutputStream stream=getOutputStream()){
    if (stream == null)     return false;
    OutputStreamWriter writer=new OutputStreamWriter(stream,StandardCharsets.UTF_8);
    if (header != null) {
      writer.append(header);
      writer.append(LINE_BREAK);
    }
    if (comments.isEmpty() || format != YAMLFormat.EXTENDED) {
      yaml.dump(root,writer);
    }
 else {
      for (      Entry<String,Object> entry : root.entrySet()) {
        String comment=comments.get(entry.getKey());
        if (comment != null) {
          writer.append(LINE_BREAK);
          writer.append(comment);
          writer.append(LINE_BREAK);
        }
        yaml.dump(Collections.singletonMap(entry.getKey(),entry.getValue()),writer);
      }
    }
    return true;
  }
 catch (  IOException ignored) {
  }
  return false;
}
