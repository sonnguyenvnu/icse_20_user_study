public static int search(Object styleMapOrValue,int index){
  if (!(styleMapOrValue instanceof StyleAttributeMap)) {
    return -1;
  }
 else {
    StyleAttributeMap styleMap=(StyleAttributeMap)styleMapOrValue;
    return styleMap.search(index);
  }
}
