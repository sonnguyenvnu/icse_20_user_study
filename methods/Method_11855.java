/** 
 * Asserts that two arrays are equal, according to the criteria defined by the concrete subclass. If they are not, an  {@link AssertionError} isthrown with the given message. If <code>expecteds</code> and <code>actuals</code> are <code>null</code>, they are considered equal.
 * @param message the identifying message for the {@link AssertionError} (<code>null</code> okay)
 * @param expecteds Object array or array of arrays (multi-dimensional array) withexpected values.
 * @param actuals Object array or array of arrays (multi-dimensional array) withactual values
 */
public void arrayEquals(String message,Object expecteds,Object actuals) throws ArrayComparisonFailure {
  arrayEquals(message,expecteds,actuals,true);
}
