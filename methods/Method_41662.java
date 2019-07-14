private void validate(){
  if (hour < 0 || hour > 23)   throw new IllegalArgumentException("Hour must be from 0 to 23");
  if (minute < 0 || minute > 59)   throw new IllegalArgumentException("Minute must be from 0 to 59");
  if (second < 0 || second > 59)   throw new IllegalArgumentException("Second must be from 0 to 59");
}
