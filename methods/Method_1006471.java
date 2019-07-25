void append(RoaringArray roaringArray){
  assert size == 0 || roaringArray.size == 0 || compareUnsigned(keys[size - 1],roaringArray.keys[0]) < 0;
  if (roaringArray.size != 0 && size != 0) {
    keys=Arrays.copyOf(keys,size + roaringArray.size);
    values=Arrays.copyOf(values,size + roaringArray.size);
    System.arraycopy(roaringArray.keys,0,keys,size,roaringArray.size);
    System.arraycopy(roaringArray.values,0,values,size,roaringArray.size);
    size+=roaringArray.size;
  }
 else   if (size == 0 && roaringArray.size != 0) {
    keys=Arrays.copyOf(roaringArray.keys,roaringArray.keys.length);
    values=Arrays.copyOf(roaringArray.values,roaringArray.values.length);
    size=roaringArray.size;
  }
}
