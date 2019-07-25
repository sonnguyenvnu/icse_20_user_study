protected void resize(int new_length){
  if (keys == null) {
    keys=new byte[Math.min(new_length,0xff)][];
    values=new byte[Math.min(new_length,0xff)][];
    return;
  }
  if (new_length > 0xff) {
    if (keys.length < 0xff)     new_length=0xff;
 else     throw new ArrayIndexOutOfBoundsException("the hashmap cannot exceed " + 0xff + " entries");
  }
  keys=Arrays.copyOf(keys,new_length);
  values=Arrays.copyOf(values,new_length);
}
