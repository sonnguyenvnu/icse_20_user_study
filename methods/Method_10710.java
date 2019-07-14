/** 
 * ?????unit????size
 * @param byteNum ???
 * @param unit    <ul><li> {@link RxConstTool.MemoryUnit#BYTE}: ??</li> <li> {@link RxConstTool.MemoryUnit#KB}  : ???</li><li> {@link RxConstTool.MemoryUnit#MB}  : ?</li><li> {@link RxConstTool.MemoryUnit#GB}  : GB</li></ul>
 * @return ?unit????size
 */
public static double byte2Size(long byteNum,RxConstTool.MemoryUnit unit){
  if (byteNum < 0) {
    return -1;
  }
switch (unit) {
default :
case BYTE:
    return (double)byteNum / BYTE;
case KB:
  return (double)byteNum / KB;
case MB:
return (double)byteNum / MB;
case GB:
return (double)byteNum / GB;
}
}
