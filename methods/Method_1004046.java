/** 
 * Adds the given file resource as a potential source file.
 * @param file file resource to add
 */
void add(final Resource file){
  resources.put(file.getName().replace(File.separatorChar,'/'),file);
}
