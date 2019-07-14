/** 
 * Orders the descriptions.
 * @return descriptions in order
 */
public List<Description> order(Collection<Description> descriptions) throws InvalidOrderingException {
  List<Description> inOrder=ordering.orderItems(Collections.unmodifiableCollection(descriptions));
  if (!ordering.validateOrderingIsCorrect()) {
    return inOrder;
  }
  Set<Description> uniqueDescriptions=new HashSet<Description>(descriptions);
  if (!uniqueDescriptions.containsAll(inOrder)) {
    throw new InvalidOrderingException("Ordering added items");
  }
  Set<Description> resultAsSet=new HashSet<Description>(inOrder);
  if (resultAsSet.size() != inOrder.size()) {
    throw new InvalidOrderingException("Ordering duplicated items");
  }
 else   if (!resultAsSet.containsAll(uniqueDescriptions)) {
    throw new InvalidOrderingException("Ordering removed items");
  }
  return inOrder;
}
