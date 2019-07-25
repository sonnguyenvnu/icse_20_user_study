/** 
 * Usage: java ThreadDumpFilter &lt;log.txt &gt;threadDump.txt
 * @param a the file name
 */
public static void main(String... a) throws Exception {
  String fileName=a[0];
  LineNumberReader in=new LineNumberReader(new BufferedReader(new FileReader(fileName)));
  PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".filtered.txt")));
  for (String s; (s=in.readLine()) != null; ) {
    if (s.startsWith("Full thread")) {
      do {
        writer.println(s);
        s=in.readLine();
      }
 while (s != null && (s.length() == 0 || " \t\"".indexOf(s.charAt(0)) >= 0));
    }
  }
  writer.close();
  in.close();
}
