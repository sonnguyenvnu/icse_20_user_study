/** 
 * @param srcFolder location where the .java source files will be placed
 * @param packageName null, or the package name that should be used as default
 * @param preprocessor the preprocessor object ready to do the work
 * @return main PApplet class name found during preprocess, or null if error
 * @throws SketchException
 */
public String preprocess(File srcFolder,String packageName,PdePreprocessor preprocessor,boolean sizeWarning) throws SketchException {
  sketch.ensureExistence();
  classPath=binFolder.getAbsolutePath();
  StringList codeFolderPackages=null;
  if (sketch.hasCodeFolder()) {
    File codeFolder=sketch.getCodeFolder();
    javaLibraryPath=codeFolder.getAbsolutePath();
    String codeFolderClassPath=Util.contentsToClassPath(codeFolder);
    classPath+=File.pathSeparator + codeFolderClassPath;
    codeFolderPackages=Util.packageListFromClassPath(codeFolderClassPath);
  }
 else {
    javaLibraryPath="";
  }
  StringBuilder bigCode=new StringBuilder();
  int bigCount=0;
  for (  SketchCode sc : sketch.getCode()) {
    if (sc.isExtension("pde")) {
      sc.setPreprocOffset(bigCount);
      bigCode.append(sc.getProgram());
      bigCode.append('\n');
      bigCount+=sc.getLineCount();
    }
  }
  SurfaceInfo sizeInfo=preprocessor.initSketchSize(sketch.getMainProgram(),sizeWarning);
  if (sizeInfo == null) {
    return null;
  }
  if (!PdePreprocessor.hasSettingsMethod(bigCode.toString()) && sizeInfo != null && sizeInfo.hasSettings()) {
    for (    String stmt : sizeInfo.getStatements()) {
      stmt=stmt.trim();
      int index=bigCode.indexOf(stmt);
      if (index != -1) {
        bigCode.delete(index,index + stmt.length());
      }
 else {
        System.err.format("Error removing '%s' from the code.",stmt);
      }
    }
  }
  PreprocessorResult result;
  try {
    File outputFolder=(packageName == null) ? srcFolder : new File(srcFolder,packageName.replace('.','/'));
    outputFolder.mkdirs();
    final File java=new File(outputFolder,sketch.getName() + ".java");
    try {
      final PrintWriter writer=PApplet.createWriter(java);
      try {
        result=preprocessor.write(writer,bigCode.toString(),codeFolderPackages);
      }
  finally {
        writer.close();
      }
    }
 catch (    RuntimeException re) {
      re.printStackTrace();
      throw new SketchException("Could not write " + java.getAbsolutePath());
    }
  }
 catch (  antlr.RecognitionException re) {
    int errorLine=re.getLine() - 1;
    int errorFile=findErrorFile(errorLine);
    errorLine-=sketch.getCode(errorFile).getPreprocOffset();
    String msg=re.getMessage();
    if (msg.contains("expecting RCURLY") || msg.contains("expecting LCURLY")) {
      for (int i=0; i < sketch.getCodeCount(); i++) {
        SketchCode sc=sketch.getCode(i);
        if (sc.isExtension("pde")) {
          String s=sc.getProgram();
          int[] braceTest=SourceUtils.checkForMissingBraces(SourceUtils.scrubCommentsAndStrings(s) + "\n",0,s.length() + 1);
          if (braceTest[0] == 0)           continue;
          throw new SketchException(braceTest[0] > 0 ? "Found an extra { character without a } to match it." : "Found an extra } character without a { to match it.",i,braceTest[1],braceTest[2],false);
        }
      }
      throw new SketchException(msg.replace("LCURLY","{").replace("RCURLY","}"),errorFile,errorLine,re.getColumn(),false);
    }
    if (msg.indexOf("expecting RBRACK") != -1) {
      System.err.println(msg);
      throw new SketchException("Syntax error, " + "maybe a missing ] character?",errorFile,errorLine,re.getColumn(),false);
    }
    if (msg.indexOf("expecting SEMI") != -1) {
      System.err.println(msg);
      throw new SketchException("Syntax error, " + "maybe a missing semicolon?",errorFile,errorLine,re.getColumn(),false);
    }
    if (msg.indexOf("expecting RPAREN") != -1) {
      System.err.println(msg);
      throw new SketchException("Syntax error, " + "maybe a missing right parenthesis?",errorFile,errorLine,re.getColumn(),false);
    }
    if (msg.indexOf("preproc.web_colors") != -1) {
      throw new SketchException("A web color (such as #ffcc00) " + "must be six digits.",errorFile,errorLine,re.getColumn(),false);
    }
    throw new SketchException(msg,errorFile,errorLine,re.getColumn(),false);
  }
catch (  antlr.TokenStreamRecognitionException tsre) {
    String locationRegex="^line (\\d+):(\\d+):\\s";
    String message=tsre.getMessage();
    String[] m;
    if (null != (m=PApplet.match(tsre.toString(),"unexpected char: (.*)"))) {
      char c=0;
      if (m[1].startsWith("0x")) {
        c=(char)PApplet.unhex(m[1].substring(2));
      }
 else       if (m[1].length() == 3) {
        c=m[1].charAt(1);
      }
 else       if (m[1].length() == 1) {
        c=m[1].charAt(0);
      }
      if (c == '\u201C' || c == '\u201D' || c == '\u2018' || c == '\u2019') {
        message=Language.interpolate("editor.status.bad_curly_quote",c);
      }
 else       if (c != 0) {
        message="Not expecting symbol " + m[1] + ", which is " + Character.getName(c) + ".";
      }
    }
    String[] matches=PApplet.match(tsre.toString(),locationRegex);
    if (matches != null) {
      int errorLine=Integer.parseInt(matches[1]) - 1;
      int errorColumn=Integer.parseInt(matches[2]);
      int errorFile=0;
      for (int i=1; i < sketch.getCodeCount(); i++) {
        SketchCode sc=sketch.getCode(i);
        if (sc.isExtension("pde") && (sc.getPreprocOffset() < errorLine)) {
          errorFile=i;
        }
      }
      errorLine-=sketch.getCode(errorFile).getPreprocOffset();
      throw new SketchException(message,errorFile,errorLine,errorColumn);
    }
 else {
      String msg=tsre.toString();
      throw new SketchException(msg,0,-1,-1);
    }
  }
catch (  SketchException pe) {
    throw pe;
  }
catch (  Exception ex) {
    System.err.println("Uncaught exception type:" + ex.getClass());
    ex.printStackTrace();
    throw new SketchException(ex.toString());
  }
  importedLibraries=new ArrayList<>();
  Library core=mode.getCoreLibrary();
  if (core != null) {
    importedLibraries.add(core);
    classPath+=core.getClassPath();
    javaLibraryPath+=File.pathSeparator + core.getNativePath();
  }
  for (  String item : result.extraImports) {
    int dot=item.lastIndexOf('.');
    String entry=(dot == -1) ? item : item.substring(0,dot);
    if (item.startsWith("static ")) {
      int dot2=item.lastIndexOf('.');
      entry=entry.substring(7,(dot2 == -1) ? entry.length() : dot2);
    }
    Library library=mode.getLibrary(entry);
    if (library != null) {
      if (!importedLibraries.contains(library)) {
        importedLibraries.add(library);
        classPath+=library.getClassPath();
        javaLibraryPath+=File.pathSeparator + library.getNativePath();
      }
    }
 else {
      boolean found=false;
      if (codeFolderPackages != null) {
        String itemPkg=entry;
        for (        String pkg : codeFolderPackages) {
          if (pkg.equals(itemPkg)) {
            found=true;
            break;
          }
        }
      }
      if (ignorableImport(entry + '.')) {
        found=true;
      }
      if (!found) {
        System.err.println("No library found for " + entry);
      }
    }
  }
  String javaClassPath=System.getProperty("java.class.path");
  if (javaClassPath.startsWith("\"") && javaClassPath.endsWith("\"")) {
    javaClassPath=javaClassPath.substring(1,javaClassPath.length() - 1);
  }
  classPath+=File.pathSeparator + javaClassPath;
  for (  SketchCode sc : sketch.getCode()) {
    if (sc.isExtension("java")) {
      String filename=sc.getFileName();
      try {
        String javaCode=sc.getProgram();
        String[] packageMatch=PApplet.match(javaCode,PACKAGE_REGEX);
        if (packageMatch == null && packageName == null) {
          sc.copyTo(new File(srcFolder,filename));
        }
 else {
          if (packageMatch == null) {
            packageMatch=new String[]{"",packageName};
            javaCode="package " + packageName + ";" + javaCode;
          }
          File packageFolder=new File(srcFolder,packageMatch[1].replace('.',File.separatorChar));
          packageFolder.mkdirs();
          Util.saveFile(javaCode,new File(packageFolder,filename));
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
        String msg="Problem moving " + filename + " to the build folder";
        throw new SketchException(msg);
      }
    }
 else     if (sc.isExtension("pde")) {
      sc.addPreprocOffset(result.headerOffset);
    }
  }
  foundMain=preprocessor.hasMethod("main");
  return result.className;
}
