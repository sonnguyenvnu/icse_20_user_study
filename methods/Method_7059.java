private static int parseGender(char gender){
switch (gender) {
case 'M':
    return Result.GENDER_MALE;
case 'F':
  return Result.GENDER_FEMALE;
default :
return Result.GENDER_UNKNOWN;
}
}
