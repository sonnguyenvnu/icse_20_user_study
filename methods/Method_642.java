public XMLGregorianCalendar createXMLGregorianCalendar(Calendar calendar){
  if (dateFactory == null) {
    try {
      dateFactory=DatatypeFactory.newInstance();
    }
 catch (    DatatypeConfigurationException e) {
      throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.",e);
    }
  }
  return dateFactory.newXMLGregorianCalendar((GregorianCalendar)calendar);
}
