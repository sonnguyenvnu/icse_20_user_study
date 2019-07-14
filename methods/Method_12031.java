/** 
 * Asserts that a list of throwables is empty. If it isn't empty, will throw  {@link MultipleFailureException} (if there aremultiple throwables in the list) or the first element in the list (if there is only one element).
 * @param errors list to check
 * @throws Exception or Error if the list is not empty
 */
@SuppressWarnings("deprecation") public static void assertEmpty(List<Throwable> errors) throws Exception {
  if (errors.isEmpty()) {
    return;
  }
  if (errors.size() == 1) {
    throw Throwables.rethrowAsException(errors.get(0));
  }
  throw new org.junit.internal.runners.model.MultipleFailureException(errors);
}
