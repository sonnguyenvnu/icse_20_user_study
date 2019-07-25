private static void execute(String fileName) throws IOException {
  fileName=FileUtils.toRealPath(fileName);
  RandomAccessFile f=new RandomAccessFile(fileName,"rw");
  long length=f.length();
  MappedByteBuffer buff=f.getChannel().map(MapMode.READ_WRITE,0,length);
  byte[] data=new byte[200];
  for (int i=0; i < length - 200; i++) {
    if (buff.get(i) != 'C' || buff.get(i + 1) != 'R' || buff.get(i + 7) != 'U' || buff.get(i + 8) != 'S') {
      continue;
    }
    buff.position(i);
    buff.get(data);
    String s=new String(data,StandardCharsets.UTF_8);
    if (!s.startsWith("CREATE USER ")) {
      continue;
    }
    int saltIndex=Utils.indexOf(s.getBytes(),"SALT ".getBytes(),0);
    if (saltIndex < 0) {
      continue;
    }
    String userName=s.substring("CREATE USER ".length(),s.indexOf("SALT ") - 1);
    if (userName.startsWith("IF NOT EXISTS ")) {
      userName=userName.substring("IF NOT EXISTS ".length());
    }
    if (userName.startsWith("\"")) {
      userName=userName.substring(1,userName.length() - 1);
    }
    System.out.println("User: " + userName);
    byte[] userPasswordHash=SHA256.getKeyPasswordHash(userName,"".toCharArray());
    byte[] salt=MathUtils.secureRandomBytes(Constants.SALT_LEN);
    byte[] passwordHash=SHA256.getHashWithSalt(userPasswordHash,salt);
    StringBuilder b=new StringBuilder();
    b.append("SALT '").append(StringUtils.convertBytesToHex(salt)).append("' HASH '").append(StringUtils.convertBytesToHex(passwordHash)).append('\'');
    byte[] replacement=b.toString().getBytes();
    buff.position(i + saltIndex);
    buff.put(replacement,0,replacement.length);
  }
  f.close();
}
