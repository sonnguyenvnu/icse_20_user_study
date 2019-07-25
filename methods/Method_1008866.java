/** 
 * Automagically finds a list of font files on local system
 * @return List&lt;URL&gt; of font files
 * @throws IOException io exception{@inheritDoc}
 */
public List find() throws IOException {
  final FontFinder fontDirFinder;
  final String osName=System.getProperty("os.name");
  if (osName.startsWith("Windows")) {
    fontDirFinder=new WindowsFontDirFinder();
  }
 else {
    if (osName.startsWith("Mac")) {
      fontDirFinder=new MacFontDirFinder();
    }
 else {
      fontDirFinder=new UnixFontDirFinder();
    }
  }
  List fontDirs=fontDirFinder.find();
  List results=new java.util.ArrayList();
  for (Iterator iter=fontDirs.iterator(); iter.hasNext(); ) {
    final File dir=(File)iter.next();
    super.walk(dir,results);
  }
  return results;
}
