/** 
 * @param element the element to convert to {@link uk.gov.gchq.gaffer.data.element.id.EntityId}.
 * @return the {@link uk.gov.gchq.gaffer.data.element.id.EntityId} extracted from the element
 */
@SuppressFBWarnings(value="BC_UNCONFIRMED_CAST",justification="If an element is not an Entity it must be an Edge") @Override public EntityId _apply(final Element element){
  final Object identifier;
  if (element instanceof Entity) {
    identifier=((Entity)element).getVertex();
  }
 else   if (IdentifierType.SOURCE == edgeIdentifierToExtract) {
    identifier=((Edge)element).getSource();
  }
 else   if (IdentifierType.DESTINATION == edgeIdentifierToExtract) {
    identifier=((Edge)element).getDestination();
  }
 else   if (IdentifierType.MATCHED_VERTEX == edgeIdentifierToExtract) {
    identifier=((Edge)element).getMatchedVertexValue();
  }
 else   if (IdentifierType.ADJACENT_MATCHED_VERTEX == edgeIdentifierToExtract) {
    identifier=((Edge)element).getAdjacentMatchedVertexValue();
  }
 else {
    throw new IllegalArgumentException("Cannot get an EntityId from an Edge when IdentifierType is " + edgeIdentifierToExtract);
  }
  return new EntitySeed(identifier);
}
