/** 
 * ??????
 * @param templatePath
 * @throws IOException
 */
public void dumpTemplate(String templatePath) throws IOException {
  BufferedWriter bw=IOUtil.newBufferedWriter(templatePath);
  String template=getTemplate();
  bw.write(template);
  bw.close();
}
