/** 
 * Attempts to halt the test and ignore it if Throwable <code>e</code> is not <code>null</code>. Similar to  {@link #assumeNoException(Throwable)}, but provides an additional message that can explain the details concerning the assumption.
 * @param e if non-null, the offending exception
 * @param message Additional message to pass to {@link AssumptionViolatedException}.
 * @see #assumeNoException(Throwable)
 */
public static void assumeNoException(String message,Throwable e){
  assumeThat(message,e,nullValue());
}
