/** 
 * Puts a key and a list of values in to the store. <br> <b>Note:</b>The method will eliminate duplicate values in the list, if any
 * @param key the key 
 * @param values list of values
 * @return the previous stored list of values
 */
public List put(Object key,List values){
  if (values == null) {
    values=new Vector(0);
  }
  Vector listWithoutDoublicates=new Vector(values.size());
  for (int i=0; i < values.size(); i++) {
    List keys=(List)backwardStore.get(values.get(i));
    if (keys == null) {
      keys=new Vector();
    }
    if (!keys.contains(key)) {
      keys.add(key);
      listWithoutDoublicates.add(values.get(i));
      backwardStore.put(values.get(i),keys);
    }
  }
  return (List)forwardStore.put(key,listWithoutDoublicates);
}
