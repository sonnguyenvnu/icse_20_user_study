/** 
 * Given a File Filter, expose as a FilenameFilter.
 * @param filter The File Filter.
 * @return A FilenameFilter.
 */
public static FilenameFilter toFilenameFilter(final Filter<File> filter){
  return new FilenameFilter(){
    @Override public boolean accept(    File dir,    String name){
      return filter.filter(new File(dir,name));
    }
    @Override public String toString(){
      return filter.toString();
    }
  }
;
}
