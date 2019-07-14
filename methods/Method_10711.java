/** 
 * ?unit????size????
 * @param size ??
 * @param unit <ul><li> {@link RxConstTool.MemoryUnit#BYTE}: ??</li> <li> {@link RxConstTool.MemoryUnit#KB}  : ???</li><li> {@link RxConstTool.MemoryUnit#MB}  : ?</li><li> {@link RxConstTool.MemoryUnit#GB}  : GB</li></ul>
 * @return ???
 */
public static long size2Byte(long size,RxConstTool.MemoryUnit unit){
  if (size < 0) {
    return -1;
  }
switch (unit) {
default :
case BYTE:
    return size * BYTE;
case KB:
  return size * KB;
case MB:
return size * MB;
case GB:
return size * GB;
}
}
