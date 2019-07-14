private static void parseBirthDate(String birthDate,Result result){
  try {
    result.birthYear=Integer.parseInt(birthDate.substring(0,2));
    result.birthYear=result.birthYear < Calendar.getInstance().get(Calendar.YEAR) % 100 - 5 ? (2000 + result.birthYear) : (1900 + result.birthYear);
    result.birthMonth=Integer.parseInt(birthDate.substring(2,4));
    result.birthDay=Integer.parseInt(birthDate.substring(4));
  }
 catch (  NumberFormatException ignore) {
  }
}
