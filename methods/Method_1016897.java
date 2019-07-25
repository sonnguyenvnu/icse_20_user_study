/** 
 * Constructs a new <code>InstanceList</code>, deserialized from <code>file</code>.  If the string value of <code>file</code> is "-", then deserialize from  {@link System.in}. 
 */
public static InstanceList load(File file){
  try {
    ObjectInputStream ois;
    if (file.toString().equals("-"))     ois=new ObjectInputStream(System.in);
 else     ois=new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
    InstanceList ilist=(InstanceList)ois.readObject();
    ois.close();
    return ilist;
  }
 catch (  ClassCastException e) {
    throw new IllegalArgumentException("*** It looks like you might be trying to load an older Mallet instance list. Mallet 2.1 breaks backwards compatibility, you may need to re-import files. ***");
  }
catch (  Exception e) {
    e.printStackTrace();
    throw new IllegalArgumentException("Couldn't read InstanceList from file " + file);
  }
}
