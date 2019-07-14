/** 
 * Adds a nested struct to the specified struct
 * @param s           the struct
 * @param fieldCount  the number of fields in the nested struct
 * @param alignment   a custom nested struct alignment, or 0 to calculate automatically
 * @param arrayLength 1 or a higher value if the nested struct is an array
 */
public static void dcSubStruct(@NativeType("DCstruct *") long s,@NativeType("DCsize") long fieldCount,@NativeType("DCint") int alignment,@NativeType("DCsize") long arrayLength){
  if (CHECKS) {
    check(s);
  }
  ndcSubStruct(s,fieldCount,alignment,arrayLength);
}
