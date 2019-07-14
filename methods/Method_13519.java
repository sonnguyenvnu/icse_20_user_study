@RequestMapping("/sms-report.do") public List<Message> smsReport(){
  return smsReportMessageListener.getSmsReportMessageSet();
}
