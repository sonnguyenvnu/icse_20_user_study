package org.springframework.cloud.alibaba.cloud.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alicloud.sms.ISmsService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.mns.model.Message;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;

@RestController
public class SmsController {

	@Autowired
	private Environment environment;

	@Autowired
	private ISmsService smsService;

	@Autowired
	private SmsReportMessageListener smsReportMessageListener;

	@GetMapping("/report-queue.do")
	public String getSmsReportQueuename(){

		return environment.getProperty("spring.cloud.alicloud.sms.up-queue-name");
	}

	/**
	 * 短信�?��? Example
	 * @param code
	 * @return
	 */
	@RequestMapping("/batch-sms-send.do")
	public SendBatchSmsResponse batchsendCheckCode(
			@RequestParam(name = "code") String code) {
		// 组装请求对象
		SendBatchSmsRequest request = new SendBatchSmsRequest();
		// 使用post�??交
		request.setMethod(MethodType.GET);
		// 必填:待�?��?手机�?�。支�?JSON格�?的批�?调用，批�?上�?为100个手机�?��?,批�?调用相对于�?��?�调用�?�时性�?有延迟,验�?�?类型的短信推�??使用�?��?�调用的方�?
		request.setPhoneNumberJson("[\"177********\",\"130********\"]");
		// 必填:短信签�??-支�?�?�?�的�?��?�?��?�?�?�的短信签�??
		request.setSignNameJson("[\"*******\",\"*******\"]");
		// 必填:短信模�?�-�?�在短信控制�?�中找到
		request.setTemplateCode("******");
		// 必填:模�?�中的�?��?替�?�JSON串,如模�?�内容为"亲爱的${name},您的验�?�?为${code}"时,此处的值为
		// �?�情�??示:如果JSON中需�?带�?�行符,请�?�照标准的JSON�??议对�?�行符的�?求,比如短信内容中包�?�\r\n的情况在JSON中需�?表示�?\\r\\n,�?�则会导致JSON在�?务端解�?失败
		request.setTemplateParamJson(
				"[{\"code\":\"" + code + "\"},{\"code\":\"" + code + "\"}]");
		// �?�选-上行短信扩展�?(扩展�?字段控制在7�?或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCodeJson("[\"90997\",\"90998\"]");
		try {
			SendBatchSmsResponse sendSmsResponse = smsService
					.sendSmsBatchRequest(request);
			return sendSmsResponse;
		}
		catch (ClientException e) {
			e.printStackTrace();
		}
		return new SendBatchSmsResponse();
	}

	/**
	 * 短信�?��? Example
	 * @param code
	 * @return
	 */
	@RequestMapping("/sms-send.do")
	public SendSmsResponse sendCheckCode(@RequestParam(name = "code") String code) {
		// 组装请求对象-具体�??述�?控制�?�-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待�?��?手机�?�
		request.setPhoneNumbers("******");
		// 必填:短信签�??-�?�在短信控制�?�中找到
		request.setSignName("******");
		// 必填:短信模�?�-�?�在短信控制�?�中找到
		request.setTemplateCode("******");
		// �?�选:模�?�中的�?��?替�?�JSON串,如模�?�内容为"亲爱的${name},您的验�?�?为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":\"" + code + "\"}");

		// 选填-上行短信扩展�?(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// �?�选:outId为�??供给业务方扩展字段,最终在短信回执消�?�中将此值带回给调用者
		request.setOutId("****TraceId");
		try {
			SendSmsResponse sendSmsResponse = smsService.sendSmsRequest(request);
			return sendSmsResponse;
		}
		catch (ClientException e) {
			e.printStackTrace();
		}
		return new SendSmsResponse();
	}

	/**
	 *
	 * 短信查询 Example
	 * @param telephone
	 * @return
	 */
	@RequestMapping("/query.do")
	public QuerySendDetailsResponse querySendDetailsResponse(
			@RequestParam(name = "tel") String telephone) {
		// 组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		// 必填-�?��?
		request.setPhoneNumber(telephone);
		// 必填-短信�?��?的日期 支�?30天内记录查询（�?�查其中一天的�?��?数�?�），格�?yyyyMMdd
		request.setSendDate("20190103");
		// 必填-页大�?
		request.setPageSize(10L);
		// 必填-当�?页�?从1开始计数
		request.setCurrentPage(1L);
		try {
			QuerySendDetailsResponse response = smsService.querySendDetails(request);
			return response;
		}
		catch (ClientException e) {
			e.printStackTrace();
		}

		return new QuerySendDetailsResponse();
	}

	@RequestMapping("/sms-report.do")
	public List<Message> smsReport() {

		return smsReportMessageListener.getSmsReportMessageSet();
	}
}
