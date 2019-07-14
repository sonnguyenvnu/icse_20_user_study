/*
 * Symphony - A modern community (forum/BBS/SNS/blog) platform written in Java.
 * Copyright (C) 2012-present, b3log.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.b3log.symphony.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.Keys;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.util.Crypts;
import org.b3log.latke.util.URLs;
import org.b3log.symphony.model.Common;
import org.json.JSONObject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mail utilities.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @author <a href="http://blog.thinkjava.top">VirutalPier</a>
 * @author <a href="https://github.com/snowflake3721">snowflake</a>
 * @version 1.2.6.9, Jun 17, 2019
 * @since 1.3.0
 */
public final class Mails {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Mails.class);

    /**
     * Template name - verifycode.
     */
    public static final String TEMPLATE_NAME_VERIFYCODE = "sym_verifycode";

    /**
     * Template name - weekly.
     */
    public static final String TEMPLATE_NAME_WEEKLY = "sym_weekly";

    /**
     * Template configuration.
     */
    private static final Configuration TEMPLATE_CFG = new Configuration(Templates.FREEMARKER_VER);

    /**
     * Domain - Channel mapping. &lt;163.com, aliyun&gt; https://github.com/b3log/symphony/issues/737
     */
    private static final Map<String, String> DOMAIN_CHANNEL = new HashMap<>();

    static {
        try {
            TEMPLATE_CFG.setDirectoryForTemplateLoading(new File(Mails.class.getResource("/mail_tpl").toURI()));
            TEMPLATE_CFG.setDefaultEncoding("UTF-8");
            TEMPLATE_CFG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            TEMPLATE_CFG.setLogTemplateExceptions(false);
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Loads mail templates failed", e);
        }

        final String mailDomains = Symphonys.MAIL_CHANNEL_MAIL_DOMAINS;
        if (StringUtils.isNotBlank(mailDomains)) {
            // aliyun:163.com,126.com;sendcloud:qq.com
            final String[] channelMaps = StringUtils.split(mailDomains, ";");
            for (int i = 0; i < channelMaps.length; i++) {
                final String channelMap = channelMaps[i];
                final String[] channelDomain = StringUtils.split(channelMap, ":");
                final String channel = channelDomain[0];
                final String[] domains = StringUtils.split(channelDomain[1], ",");
                for (int j = 0; j < domains.length; j++) {
                    final String domain = domains[j];
                    DOMAIN_CHANNEL.put(domain, channel);
                }
            }
        }
    }

    /**
     * Private constructor.
     */
    private Mails() {
    }

    /**
     * Sends a HTML mail.
     *
     * @param fromName     the specified from name
     * @param subject      the specified subject
     * @param toMail       the specified receiver mail
     * @param templateName the specified template name
     * @param dataModel    the specified data model
     */
    public static void sendHTML(final String fromName, final String subject, final String toMail,
                                final String templateName, final Map<String, Object> dataModel) {
        if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
            if (StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_API_USER) || StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_API_KEY)) {
                LOGGER.warn("Please configure [#### SendCloud Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        } else if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
            if (StringUtils.isBlank(Symphonys.MAIL_ALIYUN_AK) || StringUtils.isBlank(Symphonys.MAIL_ALIYUN_SK)) {
                LOGGER.warn("Please configure [#### Aliyun Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        } else {
            if (StringUtils.isBlank(MailSender.username) || StringUtils.isBlank(MailSender.password)) {
                LOGGER.warn("Please configure [#### Local Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        }

        Keys.fillServer(dataModel);
        Keys.fillRuntime(dataModel);
        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

        try {
            final Template template = TEMPLATE_CFG.getTemplate(templateName + ".ftl");
            final StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);
            stringWriter.close();
            final String html = stringWriter.toString();

            final String domain = StringUtils.substringAfter(toMail, "@");
            final String channel = DOMAIN_CHANNEL.getOrDefault(domain, Symphonys.MAIL_CHANNEL);
            switch (channel) {
                case "aliyun":
                    aliSendHtml(Symphonys.MAIL_ALIYUN_FROM, fromName, subject, toMail, html, Symphonys.MAIL_ALIYUN_AK, Symphonys.MAIL_ALIYUN_SK);

                    return;
                case "local":
                    MailSender.getInstance().sendHTML(fromName, subject, toMail, html);

                    return;
                case "sendcloud":
                    final Map<String, Object> formData = new HashMap<>();
                    formData.put("apiUser", Symphonys.MAIL_SENDCLOUD_API_USER);
                    formData.put("apiKey", Symphonys.MAIL_SENDCLOUD_API_KEY);
                    formData.put("from", Symphonys.MAIL_SENDCLOUD_FROM);
                    formData.put("fromName", fromName);
                    formData.put("subject", subject);
                    formData.put("to", toMail);
                    formData.put("html", html);

                    final HttpResponse response = HttpRequest.post("http://api.sendcloud.net/apiv2/mail/send").form(formData).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                            connectionTimeout(5000).timeout(5000).send();
                    response.close();
                    response.charset("UTF-8");
                    LOGGER.debug(response.bodyText());

                    return;
                default:
                    LOGGER.error("Unknown mail channel [" + channel + "]");
            }
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Sends a mail [subject=" + subject + ", to=" + toMail + "] failed", e);
        }
    }

    /**
     * Batch send HTML mails.
     *
     * @param fromName     the specified from name
     * @param subject      the specified subject
     * @param toMails      the specified receiver mails
     * @param templateName the specified template name
     * @param dataModel    the specified data model
     */
    public static void batchSendHTML(final String fromName, final String subject, final List<String> toMails,
                                     final String templateName, final Map<String, Object> dataModel) {
        if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
            if (StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_BATCH_API_USER) || StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY)) {
                LOGGER.warn("Please configure [#### SendCloud Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        } else if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
            if (StringUtils.isBlank(Symphonys.MAIL_ALIYUN_AK) || StringUtils.isBlank(Symphonys.MAIL_ALIYUN_SK)) {
                LOGGER.warn("Please configure [#### Aliyun Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        } else {
            if (StringUtils.isBlank(MailSender.username) || StringUtils.isBlank(MailSender.password)) {
                LOGGER.warn("Please configure [#### Local Mail channel ####] section in symphony.properties for sending mail");

                return;
            }
        }

        Keys.fillServer(dataModel);
        Keys.fillRuntime(dataModel);
        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

        try {
            final Map<String, Object> formData = new HashMap<>();

            formData.put("apiUser", Symphonys.MAIL_SENDCLOUD_BATCH_API_USER);
            formData.put("apiKey", Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY);
            formData.put("from", Symphonys.MAIL_SENDCLOUD_BATCH_FROM);
            formData.put("fromName", fromName);
            formData.put("subject", subject);
            formData.put("templateInvokeName", templateName);

            final Template template = TEMPLATE_CFG.getTemplate(templateName + ".ftl");
            final StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);
            stringWriter.close();
            final String html = stringWriter.toString();

            // Creates or updates the SendCloud email template
            if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
                refreshWeeklyTemplate(html);
            }

            int index = 0;
            final int size = toMails.size();
            List<String> batch = new ArrayList<>();
            HttpResponse response;
            while (index < size) {
                final String mail = toMails.get(index);
                batch.add(mail);
                index++;

                if (batch.size() > 99) {
                    if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
                        final String toMail = getStringToMailByList(batch);
                        aliSendHtml(Symphonys.MAIL_ALIYUN_BATCH_FROM, fromName, subject, toMail, html, Symphonys.MAIL_ALIYUN_AK, Symphonys.MAIL_ALIYUN_SK);

                        LOGGER.info("Sent [" + batch.size() + "] mails");
                    } else if ("local".equals(Symphonys.MAIL_CHANNEL.toLowerCase())) {
                        MailSender.getInstance().sendHTML(fromName, subject, batch, html);
                    } else {
                        try {
                            final JSONObject xsmtpapi = new JSONObject();
                            xsmtpapi.put("to", batch);
                            xsmtpapi.put("sub", new JSONObject());
                            formData.put("xsmtpapi", xsmtpapi.toString());

                            response = HttpRequest.post("http://api.sendcloud.net/apiv2/mail/sendtemplate").form(formData).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                                    connectionTimeout(5000).timeout(30000).send();
                            response.close();
                            response.charset("UTF-8");
                            LOGGER.debug(response.bodyText());

                            LOGGER.info("Sent [" + batch.size() + "] mails");
                        } catch (final Exception e) {
                            LOGGER.log(Level.ERROR, "Send mail error", e);
                        }
                    }

                    batch.clear();
                }
            }

            if (!batch.isEmpty()) { // Process remains
                if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
                    final String toMail = getStringToMailByList(batch);
                    aliSendHtml(Symphonys.MAIL_ALIYUN_BATCH_FROM, fromName, subject, toMail, html, Symphonys.MAIL_ALIYUN_AK, Symphonys.MAIL_ALIYUN_SK);
                    LOGGER.info("Sent [" + batch.size() + "] mails");
                } else if ("local".equals(Symphonys.MAIL_CHANNEL.toLowerCase())) {
                    MailSender.getInstance().sendHTML(fromName, subject, batch, html);
                } else {
                    try {
                        final JSONObject xsmtpapi = new JSONObject();
                        xsmtpapi.put("to", batch);
                        xsmtpapi.put("sub", new JSONObject());
                        formData.put("xsmtpapi", xsmtpapi.toString());

                        response = HttpRequest.post("http://api.sendcloud.net/apiv2/mail/sendtemplate").form(formData).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                                connectionTimeout(5000).timeout(30000).send();
                        response.close();
                        response.charset("UTF-8");
                        LOGGER.debug(response.bodyText());

                        LOGGER.info("Sent [" + batch.size() + "] mails");
                    } catch (final Exception e) {
                        LOGGER.log(Level.ERROR, "Send mail error", e);
                    }
                }
            }
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Batch send mail error", e);
        }
    }

    /**
     * Sends a HTML mail via Aliyun.
     *
     * @param fromName the specified from name
     * @param subject  the specified subject
     * @param toMail   the specified receiver mail
     * @param html     send html
     */
    private static void aliSendHtml(final String sendMail, final String fromName, final String subject, final String toMail,
                                    final String html, final String accessKey, final String accessSecret) {
        final Map<String, Object> map = new HashMap<>();
        map.put("Action", "SingleSendMail");
        map.put("Format", "JSON");
        map.put("Version", "2015-11-23");
        map.put("AccessKeyId", accessKey);
        map.put("SignatureMethod", "HMAC-SHA1");
        map.put("Timestamp", getISO8601Time());
        map.put("SignatureVersion", "1.0");
        map.put("SignatureNonce", String.valueOf(System.currentTimeMillis()));
        map.put("AccountName", sendMail);
        map.put("FromAlias", fromName);
        map.put("ReplyToAddress", "true");
        map.put("AddressType", "1");
        map.put("ToAddress", toMail);
        map.put("Subject", subject);
        map.put("HtmlBody", html);

        final String[] sortedKeys = map.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);
        final StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            canonicalizedQueryString.append("&")
                    .append(Mails.percentEncode(key)).append("=")
                    .append(Mails.percentEncode(map.get(key).toString()));
        }
        final StringBuffer stringToSign = new StringBuffer();
        stringToSign.append("POST");
        stringToSign.append("&");
        stringToSign.append(Mails.percentEncode("/"));
        stringToSign.append("&");
        stringToSign.append(Mails.percentEncode(canonicalizedQueryString.toString().substring(1)));

        map.put("Signature", Crypts.signHmacSHA1(stringToSign.toString(), accessSecret + "&"));

        final HttpResponse response = HttpRequest.post("https://dm.aliyuncs.com").form(map).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                connectionTimeout(5000).timeout(30000).send();
        response.close();
        response.charset("UTF-8");
        LOGGER.debug(response.bodyText());
    }

    private static String percentEncode(final String value) {
        return value != null ? URLs.encode(value).replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~") : null;
    }

    private static String getISO8601Time() {
        final Date nowDate = new Date();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));

        return df.format(nowDate);
    }

    private static String getStringToMailByList(final List<String> toMails) {
        final StringBuffer mails = new StringBuffer();
        for (String mail : toMails) {
            mails.append(mail + ",");
        }
        mails.deleteCharAt(mails.length() - 1);
        return mails.toString();
    }

    private static void refreshWeeklyTemplate(final String html) {
        final Map<String, Object> addData = new HashMap<>();
        addData.put("apiUser", Symphonys.MAIL_SENDCLOUD_BATCH_API_USER);
        addData.put("apiKey", Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY);
        addData.put("invokeName", TEMPLATE_NAME_WEEKLY);
        addData.put("name", "Weekly Newsletter");
        addData.put("subject", "Weekly Newsletter");
        addData.put("templateType", "1"); // 批�?邮件

        addData.put("html", html);
        HttpRequest.post("http://api.sendcloud.net/apiv2/template/add").form(addData).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                connectionTimeout(5000).timeout(30000).send().close();

        final Map<String, Object> updateData = new HashMap<>();
        updateData.put("apiUser", Symphonys.MAIL_SENDCLOUD_BATCH_API_USER);
        updateData.put("apiKey", Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY);
        updateData.put("invokeName", TEMPLATE_NAME_WEEKLY);

        updateData.put("html", html);
        HttpRequest.post("http://api.sendcloud.net/apiv2/template/update").form(updateData).header(Common.USER_AGENT, Symphonys.USER_AGENT_BOT).
                connectionTimeout(5000).timeout(30000).send().close();
    }
}

/**
 * 通过 JavaMail SMTP �?��?邮件.
 * <p>
 * 支�?三�?类型的邮件：
 * <ul>
 * <li>SIMPLE 简�?�类型，�?�以�?��? HTML 格�?文本，�?�从 FreeMarker 模�?�的设置</li>
 * <li>IMAGE HTML 格�?类型，�?�时会�?存 HTML 里�?�的 image 到�?盘，并生�? eml �?存到�?务器的�?置目录</li>
 * </ul>
 * </p>
 *
 * @author <a href="https://github.com/snowflake3721">snowflake</a>
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.2.2, Jun 17, 2019
 * @since 2.1.0
 */
final class MailSender implements java.io.Serializable {

    public static final String sender = Symphonys.MAIL_LOCAL_SMTP_SENDER;
    public static final String username = Symphonys.MAIL_LOCAL_SMTP_USERNAME;
    public static final String password = Symphonys.MAIL_LOCAL_SMTP_PASSWORD;

    private static final long serialVersionUID = -1000794424345267933L;
    private static final String CHARSET = "text/html;charset=UTF-8";
    private static final Logger LOGGER = Logger.getLogger(Markdowns.class);
    private static final boolean is_debug = Boolean.valueOf(Symphonys.MAIL_LOCAL_ISDEBUG);
    private static final String mail_transport_protocol = Symphonys.MAIL_LOCAL_TRANSPORT_PROTOCOL;
    private static final String mail_host = Symphonys.MAIL_LOCAL_HOST;
    private static final String mail_port = Symphonys.MAIL_LOCAL_PORT;
    private static final String mail_smtp_auth = Symphonys.MAIL_LOCAL_SMTP_AUTH;
    private static final String mail_smtp_ssl_enable = Symphonys.MAIL_LOCAL_SMTP_SSL;
    private static final String mail_smtp_starttls_enable = Symphonys.MAIL_LOCAL_SMTP_STARTTLS;
    private static MailSender mailSender;
    private static Properties prop = new Properties();

    private MailSender() {
        prop.setProperty("mail.transport.protocol", mail_transport_protocol);
        prop.setProperty("mail.host", mail_host);
        prop.setProperty("mail.port", mail_port);
        prop.setProperty("mail.smtp.auth", mail_smtp_auth);
        prop.setProperty("mail.smtp.ssl.enable", mail_smtp_ssl_enable);
        prop.setProperty("mail.smtp.starttls.enable", mail_smtp_starttls_enable);
        prop.setProperty("mail.smtp.sender", sender);
        prop.setProperty("mail.smtp.username", username);
        prop.setProperty("mail.smtp.passsword", password);
    }

    public static final MailSender getInstance() {
        if (null == mailSender) {
            mailSender = MailSenderHolder.INSTANCE;
        }
        return mailSender;
    }

    private static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地�?�
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数�?�
            img = m_image.group();
            // 匹�?<img>中的src数�?�
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    private static void setTo(String[] to, MimeMessage message) throws MessagingException {
        // 指明邮件的收件人，现在�?�件人和收件人是一样的，那就是自己给自己�?�
        if (null != to && to.length > 0) {
            if (to.length == 1) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
            } else {

                List<InternetAddress> iaList = new ArrayList<InternetAddress>();
                for (String t : to) {
                    InternetAddress ia = new InternetAddress(t);
                    iaList.add(ia);
                }
                InternetAddress[] iaArray = new InternetAddress[to.length];
                message.setRecipients(Message.RecipientType.TO, iaList.toArray(iaArray));
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(CHARSET.toLowerCase());

        /*
         * MailSender mailSender = getInstance();
         *
         * String subject = "eml with Image"; String content =
         * "这是一�?邮件正文带图片<img width=\"60px\" src=\"http://localhost:8080/images/logo-M301-161X105.png\" />的邮件"
         * ; String[] tos = { "bruceyang_it@163.com" };
         * mailSender.sendMessage(tos, subject, content, saved_path,
         * MailType.IMAGE);
         */

    }

    private Object readResolve() {
        return MailSenderHolder.INSTANCE;
    }

    public void sendMessage(String[] tos, String subject, String content) throws Exception {
        // 1�?创建session
        Session session = Session.getInstance(prop);
        // 开�?�Session的debug模�?，这样就�?�以查看到程�?�?��?Email的�?行状�?
        session.setDebug(is_debug);
        // 2�?通过session得到transport对象
        Transport ts = session.getTransport();
        // 3�?使用邮箱的用户�??和密�?连上邮件�?务器，�?��?邮件时，�?�件人需�?�??交邮箱的用户�??和密�?给smtp�?务器，用户�??和密�?都通过验�?之�?��?能够正常�?��?邮件给收件人。
        ts.connect(mail_host, Integer.valueOf(mail_port), username, password);
        // 4�?创建邮件
        MimeMessage message = new MimeMessage(session);
        createTextMail(message, sender, tos, subject, content);
        // 5�?�?��?邮件
        ts.sendMessage(message, message.getAllRecipients());
        LOGGER.debug(Arrays.toString(tos));
        LOGGER.debug(subject);
        LOGGER.debug(content);
        ts.close();
    }

    /**
     * 创建一�?�?�包�?�文本的邮件.
     *
     * @param message
     * @param from
     * @param to
     * @param subject
     * @param content
     * @return
     * @throws Exception
     */
    public MimeMessage createTextMail(MimeMessage message, String from, String[] to, String subject, String content) throws Exception {
        // 指明邮件的�?�件人
        message.setFrom(new InternetAddress(from));
        setTo(to, message);

        // 邮件的标题,�?�包�?�文本的简�?�邮件

        if (StringUtils.isEmpty(subject)) {
            subject = "email from the system";
        }
        message.setSubject(StringUtils.trimToEmpty(subject));
        // 邮件的文本内容
        message.setContent(content, CHARSET);
        // 返回创建好的邮件对象

        return message;
    }

    /**
     * Sends a HTML mail for toMailList.
     *
     * @param fromName
     * @param subject
     * @param toMailList
     * @param html
     */
    public void sendHTML(final String fromName, final String subject, final List<String> toMailList, String html) {
        if (null != toMailList && toMailList.size() > 0) {
            sendHTML(fromName, subject, toMailList.toArray(new String[toMailList.size()]), html);
        }
    }

    /**
     * Sends a HTML mail.
     *
     * @param fromName
     * @param subject
     * @param toMailSingle
     * @param html
     */
    public void sendHTML(final String fromName, final String subject, final String toMailSingle, String html) {
        sendHTML(fromName, subject, new String[]{toMailSingle}, html);
    }

    private void sendHTML(final String fromName, final String subject, final String[] toMail, final String html) {
        try {
            getInstance().sendMessage(toMail, subject, html);
            LOGGER.debug(html);

        } catch (Exception e) {
            LOGGER.log(Level.ERROR, "Send mail error", e);
        }
    }

    private static class MailSenderHolder {
        private static final MailSender INSTANCE = new MailSender();
    }
}
