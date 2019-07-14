/** 
 * Stores name to temporary stack. Used when name's value may or may not be serialized (e.g. it may be excluded), in that case we do not need to write the name.
 */
public void pushName(final String name,final boolean withComma){
  pushedName=name;
  pushedComma=withComma;
  isPushed=true;
}
