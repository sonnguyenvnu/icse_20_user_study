/** 
 * Compresses a given directory in its own location. <p> A ZIP file will be first created with a temporary name. After the compressing the directory will be deleted and the ZIP file will be renamed as the original directory.
 * @param dir input directory as well as the target ZIP file.
 * @see #pack(File,File)
 */
public static void unexplode(File dir){
  unexplode(dir,DEFAULT_COMPRESSION_LEVEL);
}
