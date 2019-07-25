/** 
 * Await until a  {@link java.util.concurrent.Callable} returns <code>true</code>. This is methodis not as generic as the other variants of "until" but it allows for a more precise and in some cases even more english-like syntax. E.g. <p>&nbsp;</p> <pre> await().until(numberOfPersonsIsEqualToThree()); </pre> <p>&nbsp;</p> where "numberOfPersonsIsEqualToThree()" returns a standard {@link java.util.concurrent.Callable} of type {@link java.lang.Boolean}: <p>&nbsp;</p> <pre> private Callable&lt;Boolean&gt; numberOfPersons() { return new Callable&lt;Boolean&gt;() { public Boolean call() { return personRepository.size() == 3; } }; } </pre>
 * @param conditionEvaluator the condition evaluator
 * @throws org.awaitility.core.ConditionTimeoutException If condition was not fulfilled within the given time period.
 */
public void until(Callable<Boolean> conditionEvaluator){
  until(new CallableCondition(conditionEvaluator,generateConditionSettings()));
}
