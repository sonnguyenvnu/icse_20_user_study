private static double calcHours(String s){
  String[] arr=s.split(":");
  return (Integer.parseInt(arr[0]) * 3600 + Integer.parseInt(arr[1]) * 60 + Integer.parseInt(arr[2])) * 1.0 / 3600;
}
