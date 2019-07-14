/** 
 * Loads a  {@link MaxHistory} from {@code file}, or generates a new one that will be saved to  {@code file}.
 */
public static MaxHistory forFolder(File file){
  if (file.exists()) {
    try {
      return readHistory(file);
    }
 catch (    CouldNotReadCoreException e) {
      e.printStackTrace();
      file.delete();
    }
  }
  return new MaxHistory(file);
}
