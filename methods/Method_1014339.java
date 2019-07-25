/** 
 * Returns a  {@link LittleField} wrapping this field, effectively convertingit to little endian.
 * @return a little-endian version of this field
 */
public Field<T> little(){
  return new LittleField<>(this);
}
