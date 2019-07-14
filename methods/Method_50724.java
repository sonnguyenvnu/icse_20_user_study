private boolean validateChecksum(String literal){
  final String part1=literal.substring(0,5);
  final String part2=literal.substring(5,10);
  final String part3=literal.substring(10,15);
  final char checksum1=checksum(part1);
  final char checksum2=checksum(part2);
  final char checksum3=checksum(part3);
  return literal.charAt(15) == checksum1 && literal.charAt(16) == checksum2 && literal.charAt(17) == checksum3;
}
