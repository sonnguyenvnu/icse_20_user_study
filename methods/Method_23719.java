protected void create(String key,String value){
  if (count == keys.length) {
    keys=PApplet.expand(keys);
    values=PApplet.expand(values);
  }
  indices.put(key,Integer.valueOf(count));
  keys[count]=key;
  values[count]=value;
  count++;
}
