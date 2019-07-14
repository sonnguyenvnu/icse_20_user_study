private static void createUser(int count) throws Exception {
  List<MiaoshaUser> users=new ArrayList<MiaoshaUser>(count);
  for (int i=0; i < count; i++) {
    MiaoshaUser user=new MiaoshaUser();
    user.setId(100L + i);
    user.setLoginCount(1);
    user.setNickname(String.valueOf(13000000000L + i));
    user.setRegisterDate(new Date());
    user.setSalt("1a2b3c");
    user.setPassword(MD5Util.inputPassToDbPass("123456",user.getSalt()));
    users.add(user);
  }
  System.out.println("create user");
  Connection conn=DBUtil.getConn();
  String sql="insert into miaosha_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
  PreparedStatement pstmt=conn.prepareStatement(sql);
  for (int i=0; i < users.size(); i++) {
    MiaoshaUser user=users.get(i);
    pstmt.setInt(1,user.getLoginCount());
    pstmt.setString(2,user.getNickname());
    pstmt.setTimestamp(3,new Timestamp(user.getRegisterDate().getTime()));
    pstmt.setString(4,user.getSalt());
    pstmt.setString(5,user.getPassword());
    pstmt.setLong(6,user.getId());
    pstmt.addBatch();
  }
  pstmt.executeBatch();
  pstmt.close();
  conn.close();
  System.out.println("insert to db");
  String urlString="http://localhost:8080/login/create_token";
  File file=new File("D:/tokens.txt");
  if (file.exists()) {
    file.delete();
  }
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  file.createNewFile();
  raf.seek(0);
  for (int i=0; i < users.size(); i++) {
    MiaoshaUser user=users.get(i);
    URL url=new URL(urlString);
    HttpURLConnection co=(HttpURLConnection)url.openConnection();
    co.setRequestMethod("POST");
    co.setDoOutput(true);
    OutputStream out=co.getOutputStream();
    String params="mobile=" + user.getNickname() + "&password=" + MD5Util.inputPassToFormPass("123456");
    out.write(params.getBytes());
    out.flush();
    InputStream inputStream=co.getInputStream();
    ByteArrayOutputStream bout=new ByteArrayOutputStream();
    byte buff[]=new byte[1024];
    int len=0;
    while ((len=inputStream.read(buff)) >= 0) {
      bout.write(buff,0,len);
    }
    inputStream.close();
    bout.close();
    String response=new String(bout.toByteArray());
    System.out.println("create token : " + user.getId());
    String row=user.getNickname() + "," + response;
    raf.seek(raf.length());
    raf.write(row.getBytes());
    raf.write("\r\n".getBytes());
    System.out.println("write to file : " + user.getId());
  }
  raf.close();
  System.out.println("over");
}
