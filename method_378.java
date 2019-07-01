/** 
 * Finds the TiffOutputField for the given tag from this TiffOutputDirectory. <p> If there is no field matching the given tag, null will be returned. </p>
 * @param tag the tag specifying the field
 * @return the field matching tagInfo or null, if the field isn't present
 * @see #findField(TagInfo)
 */
public TiffOutputField _XXXXX_(final int tag){
  for (  final TiffOutputField field : fields) {
    if (field.tag == tag) {
      return field;
    }
  }
  return null;
}