@RequestMapping("sendInlineMail") public String sendInlineMail(){
  MimeMessage message=null;
  try {
    message=jms.createMimeMessage();
    MimeMessageHelper helper=new MimeMessageHelper(message,true);
    helper.setFrom(from);
    helper.setTo("888888@qq.com");
    helper.setSubject("??????????");
    helper.setText("<html><body>????<img src='cid:img'/></body></html>",true);
    FileSystemResource file=new FileSystemResource(new File("src/main/resources/static/img/sunshine.png"));
    helper.addInline("img",file);
    jms.send(message);
    return "????";
  }
 catch (  Exception e) {
    e.printStackTrace();
    return e.getMessage();
  }
}
