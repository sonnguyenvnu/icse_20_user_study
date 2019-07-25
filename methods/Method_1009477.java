/** 
 * Creates a sequence of FileSource objects via mapping a sequence of files to the sequence of corresponding names for the entries
 * @param files file array to form the data of the objectsin the resulting array
 * @param names file array to form the names of the objectsin the resulting array
 * @return array of FileSource objects created by mappinggiven files array to the given names array one by one
 * @throws java.lang.IllegalArgumentException if the names arraycontains less items than the files array
 */
public static FileSource[] pair(File[] files,String[] names){
  if (files.length > names.length) {
    throw new IllegalArgumentException("names array must contain " + "at least the same amount of items as files array or more");
  }
  FileSource[] result=new FileSource[files.length];
  for (int i=0; i < files.length; i++) {
    result[i]=new FileSource(names[i],files[i]);
  }
  return result;
}
