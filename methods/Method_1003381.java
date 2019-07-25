private void resources(boolean clientOnly,boolean basicOnly){
  if (!clientOnly) {
    java("org.h2.build.doc.GenerateHelp",null);
    javadoc("-sourcepath","src/main","org.h2.tools","org.h2.jmx","-classpath","ext/lucene-core-5.5.5.jar" + File.pathSeparator + "ext/lucene-analyzers-common-5.5.5.jar" + File.pathSeparator + "ext/lucene-queryparser-5.5.5.jar" + File.pathSeparator + "ext/jts-core-1.15.0.jar","-docletpath","bin" + File.pathSeparator + "temp","-doclet","org.h2.build.doclet.ResourceDoclet");
  }
  FileList files=files("src/main").exclude("*.MF").exclude("*.java").exclude("*/package.html").exclude("*/java.sql.Driver").exclude("*.DS_Store");
  if (basicOnly) {
    files=files.keep("src/main/org/h2/res/_messages_en.*");
  }
  if (clientOnly) {
    files=files.exclude("src/main/org/h2/res/help.csv");
    files=files.exclude("src/main/org/h2/res/h2*");
    files=files.exclude("src/main/org/h2/res/javadoc.properties");
    files=files.exclude("src/main/org/h2/server/*");
  }
  zip("temp/org/h2/util/data.zip",files,"src/main",true,false);
}
