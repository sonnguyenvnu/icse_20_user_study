protected void writeEntryCSV(PrintWriter writer,String entry){
  if (entry != null) {
    if (entry.indexOf('\"') != -1) {
      char[] c=entry.toCharArray();
      writer.print('\"');
      for (int i=0; i < c.length; i++) {
        if (c[i] == '\"') {
          writer.print("\"\"");
        }
 else {
          writer.print(c[i]);
        }
      }
      writer.print('\"');
    }
 else     if (entry.indexOf(',') != -1 || entry.indexOf('\n') != -1 || entry.indexOf('\r') != -1) {
      writer.print('\"');
      writer.print(entry);
      writer.print('\"');
    }
 else     if ((entry.length() > 0) && (entry.charAt(0) == ' ' || entry.charAt(entry.length() - 1) == ' ')) {
      writer.print('\"');
      writer.print(entry);
      writer.print('\"');
    }
 else {
      writer.print(entry);
    }
  }
}
