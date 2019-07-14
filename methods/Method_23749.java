void saveNumberODS(OutputStream output,String text) throws IOException {
  writeUTF(output,"          <table:table-cell office:value-type=\"float\" office:value=\"" + text + "\">","            <text:p>" + text + "</text:p>","          </table:table-cell>");
}
