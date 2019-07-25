/** 
 * Returns the type descriptor of the local variable specified by <code>local_variable_table[i].descriptor_index</code>. <p> If this attribute represents a LocalVariableTypeTable attribute, this method returns the type signature of the local variable specified by <code>local_variable_type_table[i].signature_index</code>.
 * @param i         the i-th entry.
 */
public String descriptor(int i){
  return getConstPool().getUtf8Info(descriptorIndex(i));
}
