/** 
 * Converts ArrayList of HybridFileParcelable to ArrayList of File
 */
public static ArrayList<File> hybridListToFileArrayList(ArrayList<HybridFileParcelable> a){
  ArrayList<File> b=new ArrayList<>();
  for (int i=0; i < a.size(); i++) {
    b.add(new File(a.get(i).getPath()));
  }
  return b;
}
