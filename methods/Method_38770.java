/** 
 * Reads transaction mode from method annotation. Annotations are cached for better performances.
 * @param type target class
 * @param methodName target method name over which the transaction should be wrapped
 * @param methodArgTypes types of arguments, used to find the method
 * @param unique unique method fingerprint that contains return and arguments type information
 */
public synchronized JtxTransactionMode getTxMode(final Class type,final String methodName,final Class[] methodArgTypes,final String unique){
  String signature=type.getName() + '#' + methodName + '%' + unique;
  JtxTransactionMode txMode=txmap.get(signature);
  if (txMode == null) {
    if (!txmap.containsKey(signature)) {
      final Method m;
      try {
        m=type.getMethod(methodName,methodArgTypes);
      }
 catch (      NoSuchMethodException nsmex) {
        throw new ProxettaException(nsmex);
      }
      final TransactionAnnotationValues txAnn=readTransactionAnnotation(m);
      if (txAnn != null) {
        txMode=new JtxTransactionMode(txAnn.propagation(),txAnn.isolation(),txAnn.readOnly(),txAnn.timeout());
      }
 else {
        txMode=defaultTransactionMode;
      }
      txmap.put(signature,txMode);
    }
  }
  return txMode;
}
