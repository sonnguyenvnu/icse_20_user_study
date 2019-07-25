private void run(String... args) throws Exception {
  String dir="docs";
  String destDir="docs/html";
  for (int i=0; i < args.length; i++) {
    if (args[i].equals("-dir")) {
      dir=args[++i];
    }
 else     if (args[i].equals("-destDir")) {
      destDir=args[++i];
    }
  }
  File file=new File(dir);
  setNoIndex("index.html","html/header.html","html/search.html","html/frame.html","html/fragments.html","html/sourceError.html","html/source.html","html/mainWeb.html","javadoc/index.html","javadoc/classes.html","javadoc/allclasses-frame.html","javadoc/allclasses-noframe.html","javadoc/constant-values.html","javadoc/overview-frame.html","javadoc/overview-summary.html","javadoc/serialized-form.html");
  output=new PrintWriter(new FileWriter(destDir + "/index.js"));
  readPages("",file,0);
  output.println("var pages=new Array();");
  output.println("var ref=new Array();");
  output.println("var ignored='';");
  output.println("function Page(title, file) { ");
  output.println("    this.title=title; this.file=file;");
  output.println("}");
  output.println("function load() {");
  sortWords();
  removeOverflowRelations();
  sortPages();
  listPages();
  listWords();
  output.println("}");
  output.close();
}
