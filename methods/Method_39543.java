/** 
 * Returns the start offset in this  {@link ClassReader} of a JVMS 'cp_info' structure (i.e. aconstant pool entry), plus one. <i>This method is intended for  {@link Attribute} sub classes,and is normally not needed by class generators or adapters.</i>
 * @param constantPoolEntryIndex the index a constant pool entry in the class's constant pooltable.
 * @return the start offset in this {@link ClassReader} of the corresponding JVMS 'cp_info'structure, plus one.
 */
public int getItem(final int constantPoolEntryIndex){
  return cpInfoOffsets[constantPoolEntryIndex];
}
