/** 
 * Constructs a  {@code Statement} to run all of the tests in the test class.Override to add pre-/post-processing. Here is an outline of the implementation: <ol> <li>Determine the children to be run using  {@link #getChildren()}(subject to any imposed filter and sort).</li> <li>If there are any children remaining after filtering and ignoring, construct a statement that will: <ol> <li>Apply all  {@code ClassRule}s on the test-class and superclasses.</li> <li>Run all non-overridden  {@code @BeforeClass} methods on the test-classand superclasses; if any throws an Exception, stop execution and pass the exception on.</li> <li>Run all remaining tests on the test-class.</li> <li>Run all non-overridden  {@code @AfterClass} methods on the test-classand superclasses: exceptions thrown by previous steps are combined, if necessary, with exceptions from AfterClass methods into a {@link org.junit.runners.model.MultipleFailureException}.</li> </ol> </li> </ol>
 * @return {@code Statement}
 */
protected Statement classBlock(final RunNotifier notifier){
  Statement statement=childrenInvoker(notifier);
  if (!areAllChildrenIgnored()) {
    statement=withBeforeClasses(statement);
    statement=withAfterClasses(statement);
    statement=withClassRules(statement);
    statement=withInterruptIsolation(statement);
  }
  return statement;
}
