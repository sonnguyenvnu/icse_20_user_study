/** 
 * Adds an UTF8 string to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute} subclasses, and is normally not needed by class generators or adapters.</i>
 * @param value the String value.
 * @return the index of a new or already existing UTF8 item.
 */
public int newUTF8(final String value){
  return symbolTable.addConstantUtf8(value);
}
