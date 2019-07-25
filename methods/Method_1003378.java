/** 
 * Create the documentation from the documentation sources. API Javadocs are created as well.
 */
@Description(summary="Create the documentation from sources (incl. API Javadocs).") public void docs(){
  javadoc();
  copy("docs",files("src/docsrc/index.html"),"src/docsrc");
  java("org.h2.build.doc.XMLChecker",null);
  java("org.h2.build.code.CheckJavadoc",null);
  java("org.h2.build.code.CheckTextFiles",null);
  java("org.h2.build.doc.GenerateDoc",null);
  java("org.h2.build.doc.GenerateHelp",null);
  java("org.h2.build.i18n.PrepareTranslation",null);
  java("org.h2.build.indexer.Indexer",null);
  java("org.h2.build.doc.MergeDocs",null);
  java("org.h2.build.doc.WebSite",null);
  java("org.h2.build.doc.LinkChecker",null);
  java("org.h2.build.doc.XMLChecker",null);
  java("org.h2.build.doc.SpellChecker",null);
  java("org.h2.build.code.CheckTextFiles",null);
  beep();
}
