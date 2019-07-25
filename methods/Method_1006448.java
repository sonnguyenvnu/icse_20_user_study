void append(MutableRoaringArray appendage){
  assert size == 0 || appendage.size == 0 || compareUnsigned(keys[size - 1],appendage.keys[0]) < 0;
  if (appendage.size != 0 && size != 0) {
    keys=Arrays.copyOf(keys,size + appendage.size);
    values=Arrays.copyOf(values,size + appendage.size);
    System.arraycopy(appendage.keys,0,keys,size,appendage.size);
    System.arraycopy(appendage.values,0,values,size,appendage.size);
    size+=appendage.size;
  }
 else   if (size == 0 && appendage.size != 0) {
    keys=Arrays.copyOf(appendage.keys,appendage.keys.length);
    values=Arrays.copyOf(appendage.values,appendage.values.length);
    size=appendage.size;
  }
}
