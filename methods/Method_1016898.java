/** 
 * Saves this <code>InstanceList</code> to <code>file</code>. If the string value of <code>file</code> is "-", then serialize to  {@link System.out}. 
 */
public void save(File file){
  try {
    ObjectOutputStream ois;
    if (file.toString().equals("-")) {
      ois=new ObjectOutputStream(System.out);
    }
 else {
      ois=new ObjectOutputStream(new FileOutputStream(file));
    }
    ois.writeObject(this);
    ois.close();
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new IllegalArgumentException("Couldn't save InstanceList to file " + file);
  }
}
