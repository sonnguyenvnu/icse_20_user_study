/** 
 * Clean all jar files, classes, and generated documentation.
 */
@Description(summary="Clean all jar files, classes, and generated documentation.") public void clean(){
  delete("temp");
  delete("docs");
  mkdir("docs");
  mkdir("bin");
  delete(files(".").keep("*/Thumbs.db"));
}
