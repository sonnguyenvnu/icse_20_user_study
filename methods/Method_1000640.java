/** 
 * ??File?????,???Jar?????????!! <b>????</b>
 */
protected static InputStream _input(File file) throws IOException {
  if (file.exists())   return new FileInputStream(file);
  if (Scans.isInJar(file)) {
    NutResource nutResource=Scans.makeJarNutResource(file);
    if (nutResource != null)     return nutResource.getInputStream();
  }
  throw new FileNotFoundException(file.toString());
}
