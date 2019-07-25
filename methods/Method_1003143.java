/** 
 * Runs this tool. Options are case sensitive. Supported options are: <table summary="command line options"> <tr><td>[-dump &lt;fileName&gt;]</td> <td>Dump the contends of the file</td></tr> <tr><td>[-info &lt;fileName&gt;]</td> <td>Get summary information about a file</td></tr> <tr><td>[-compact &lt;fileName&gt;]</td> <td>Compact a store</td></tr> <tr><td>[-compress &lt;fileName&gt;]</td> <td>Compact a store with compression enabled</td></tr> </table>
 * @param args the command line arguments
 */
public static void main(String... args){
  for (int i=0; i < args.length; i++) {
    if ("-dump".equals(args[i])) {
      String fileName=args[++i];
      dump(fileName,new PrintWriter(System.out),true);
    }
 else     if ("-info".equals(args[i])) {
      String fileName=args[++i];
      info(fileName,new PrintWriter(System.out));
    }
 else     if ("-compact".equals(args[i])) {
      String fileName=args[++i];
      compact(fileName,false);
    }
 else     if ("-compress".equals(args[i])) {
      String fileName=args[++i];
      compact(fileName,true);
    }
 else     if ("-rollback".equals(args[i])) {
      String fileName=args[++i];
      long targetVersion=Long.decode(args[++i]);
      rollback(fileName,targetVersion,new PrintWriter(System.out));
    }
 else     if ("-repair".equals(args[i])) {
      String fileName=args[++i];
      repair(fileName);
    }
  }
}
