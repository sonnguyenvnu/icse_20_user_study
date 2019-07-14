/** 
 * Return the XML document formatted with two spaces for indents. Chosen to do this since it's the most common case (e.g. with println()). Same as format(2). Use the format() function for more options.
 * @webref xml:method
 * @brief Gets XML data as a String using default formatting
 * @return the content
 * @see XML#format(int)
 */
@Override public String toString(){
  return format(-1);
}
