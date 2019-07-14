/** 
 * Increment the counter for a combination of  {@code bugChecker}'s canonical name and  {@code key}by  {@code count}. <p>e.g.: a key of  {@code foo} becomes {@code FooChecker-foo}.
 */
public void incrementCounter(BugChecker bugChecker,String key,int count){
  statisticsCollector.incrementCounter(statsKey(bugChecker.canonicalName() + "-" + key),count);
}
