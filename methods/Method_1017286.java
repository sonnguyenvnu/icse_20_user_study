@Override public void run(Race.Registration registration) throws Exception {
  JsonObject jsonObject=Json.createObjectBuilder().add("firstName","John").add("lastName","Smith").add("age",25).add("address",Json.createObjectBuilder().add("streetAddress","21 2nd Street").add("city","New York").add("state","NY").add("postalCode","10021")).add("phoneNumber",Json.createArrayBuilder().add(Json.createObjectBuilder().add("type","home").add("number","212 555-1234")).add(Json.createObjectBuilder().add("type","fax").add("number","646 555-4567"))).build();
  StringWriter stringWriter=new StringWriter();
  try (JsonWriter writer=Json.createWriter(stringWriter)){
    writer.write(jsonObject);
  }
   String toString=stringWriter.toString();
  JsonObject fromString=null;
  try (JsonReader jsonReader=Json.createReader(new StringReader(toString))){
    fromString=jsonReader.readObject();
  }
   if (!jsonObject.equals(fromString)) {
    throw new IllegalStateException("json object read from string does not equal the one built");
  }
}
