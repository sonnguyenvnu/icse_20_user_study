/** 
 * @param element the {@link uk.gov.gchq.gaffer.data.element.Element} to validate
 * @return true if the {@link uk.gov.gchq.gaffer.data.element.Element} is an instance of an {@link uk.gov.gchq.gaffer.data.element.Edge}.
 */
@Override public boolean validate(final Element element){
  return element instanceof Edge;
}
