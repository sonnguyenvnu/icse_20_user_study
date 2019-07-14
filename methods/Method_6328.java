public static String formatName(String firstName,String lastName){
  if (firstName != null) {
    firstName=firstName.trim();
  }
  if (lastName != null) {
    lastName=lastName.trim();
  }
  StringBuilder result=new StringBuilder((firstName != null ? firstName.length() : 0) + (lastName != null ? lastName.length() : 0) + 1);
  if (LocaleController.nameDisplayOrder == 1) {
    if (firstName != null && firstName.length() > 0) {
      result.append(firstName);
      if (lastName != null && lastName.length() > 0) {
        result.append(" ");
        result.append(lastName);
      }
    }
 else     if (lastName != null && lastName.length() > 0) {
      result.append(lastName);
    }
  }
 else {
    if (lastName != null && lastName.length() > 0) {
      result.append(lastName);
      if (firstName != null && firstName.length() > 0) {
        result.append(" ");
        result.append(firstName);
      }
    }
 else     if (firstName != null && firstName.length() > 0) {
      result.append(firstName);
    }
  }
  return result.toString();
}
