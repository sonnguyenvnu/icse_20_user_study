/** 
 * Reads lines from source  {@link File} with specified encoding and returns lines as {@link String}s in array.
 * @param file     {@link File} to read.
 * @param encoding Endoing to use.
 * @return array of Strings which represents lines in the {@link File}.
 * @throws IOException if {@link File} does not exist or is not a {@link File} or there is an issue readingthe  {@link File}.
 */
public static String[] readLines(final File file,final String encoding) throws IOException {
  checkExists(file);
  checkIsFile(file);
  List<String> list=new ArrayList<>();
  InputStream in=streamOf(file,encoding);
  try {
    BufferedReader br=new BufferedReader(StreamUtil.inputStreamReadeOf(in,encoding));
    String strLine;
    while ((strLine=br.readLine()) != null) {
      list.add(strLine);
    }
  }
  finally {
    StreamUtil.close(in);
  }
  return list.toArray(new String[0]);
}
