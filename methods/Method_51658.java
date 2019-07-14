/** 
 * Given a FilenameFilter, expose as a File Filter.
 * @param filter The FilenameFilter.
 * @return A File Filter.
 */
public static Filter<File> toFileFilter(final FilenameFilter filter){
  return new Filter<File>(){
    @Override public boolean filter(    File file){
      return filter.accept(file.getParentFile(),file.getName());
    }
    @Override public String toString(){
      return filter.toString();
    }
  }
;
}
