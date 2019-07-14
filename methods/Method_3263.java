public boolean open(InputStream stream){
  try {
    ObjectInputStream ois=new ObjectInputStream(stream);
    int version=(Integer)ois.readObject();
    costFactor_=(Double)ois.readObject();
    maxid_=(Integer)ois.readObject();
    xsize_=(Integer)ois.readObject();
    y_=(List<String>)ois.readObject();
    unigramTempls_=(List<String>)ois.readObject();
    bigramTempls_=(List<String>)ois.readObject();
    dat=(MutableDoubleArrayTrieInteger)ois.readObject();
    alpha_=(double[])ois.readObject();
    ois.close();
    return true;
  }
 catch (  Exception e) {
    e.printStackTrace();
    return false;
  }
}
