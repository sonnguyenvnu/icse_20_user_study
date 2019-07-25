/** 
 * Generic method that gets an instance of an entry type (article, book, booklet ...). It also gets one bibEntry. Then the method checks all fields of the entry and then for all fields the method uses the set method of the entry type with the fieldname. So for example if a bib entry has the field author and the value for it is "Max Mustermann" and the given type is an article, then this method will invoke <Code>article.setAuthor("Max Mustermann")</Code>. <br> <br> The second part of this method is that the entry type will be set to the entry. So e.g., if the type is article then <Code>entry.setArticle(article)</Code> will be invoked.
 * @param entryType The type parameterized type of the entry.
 * @param bibEntry  The bib entry, which fields will be set to the entryType.
 * @param entry     The bibtexml entry. The entryType will be set to this entry.
 */
private <T>void parse(T entryType,BibEntry bibEntry,Entry entry){
  List<Method> declaredSetMethods=getListOfSetMethods(entryType);
  for (  Map.Entry<String,String> entryField : bibEntry.getFieldMap().entrySet()) {
    String key=entryField.getKey();
    String value=entryField.getValue();
    for (    Method method : declaredSetMethods) {
      String methodNameWithoutSet=method.getName().replace("set","").toLowerCase(ENGLISH);
      if (!methodNameWithoutSet.equals(key)) {
        continue;
      }
      try {
        if ("year".equals(key)) {
          try {
            XMLGregorianCalendar calendar=DatatypeFactory.newInstance().newXMLGregorianCalendar(value);
            method.invoke(entryType,calendar);
          }
 catch (          DatatypeConfigurationException e) {
            LOGGER.error("A configuration error occured");
          }
          break;
        }
 else         if ("number".equals(key)) {
          try {
            method.invoke(entryType,new BigInteger(value));
          }
 catch (          NumberFormatException exception) {
            LOGGER.warn("The value %s of the 'number' field is not an integer and thus is ignored for the export",value);
          }
          break;
        }
 else {
          method.invoke(entryType,value);
          break;
        }
      }
 catch (      IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
        LOGGER.error("Could not invoke method " + method.getName(),e);
      }
    }
    List<Method> entryMethods=getListOfSetMethods(entry);
    for (    Method method : entryMethods) {
      String methodWithoutSet=method.getName().replace("set","");
      String simpleClassName=entryType.getClass().getSimpleName();
      if (methodWithoutSet.equals(simpleClassName)) {
        try {
          method.invoke(entry,entryType);
        }
 catch (        IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
          LOGGER.warn("Could not set the type to the entry");
        }
      }
    }
  }
}
