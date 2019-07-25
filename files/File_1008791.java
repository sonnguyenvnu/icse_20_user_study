package org.xxpay.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
public class HttpClient {

    private static final String USER_AGENT_VALUE =
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";

    private static final String JKS_CA_FILENAME =
            "tenpay_cacert.jks";

    private static final String JKS_CA_ALIAS = "tenpay";

    private static final String JKS_CA_PASSWORD = "";

    private static Logger _log = LoggerFactory.getLogger(HttpClient.class);

    /**
     * ca�?书文件
     */
    private File caFile;

    /**
     * �?书文件
     */
    private File certFile;

    /**
     * �?书密�?
     */
    private String certPasswd;

    /**
     * 请求内容，无论post和get，都用get方�?�??供
     */
    private String reqContent;

    /**
     * 应答内容
     */
    private String resContent;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 错误信�?�
     */
    private String errInfo;

    /**
     * 超时时间,以秒为�?��?
     */
    private int timeOut;

    /**
     * http应答编�?
     */
    private int responseCode;

    /**
     * 字符编�?
     */
    private String charset;

    private InputStream inputStream;

    public HttpClient() {
        this.caFile = null;
        this.certFile = null;
        this.certPasswd = "";

        this.reqContent = "";
        this.resContent = "";
        this.method = "POST";
        this.errInfo = "";
        this.timeOut = 30;//30秒

        this.responseCode = 0;
        this.charset = "UTF-8";

        this.inputStream = null;
    }

    public HttpClient(String url, String method, int timeOut, String charset) {
        this.caFile = null;
        this.certFile = null;
        this.certPasswd = "";

        this.reqContent = url;
        this.resContent = "";
        this.method = method;
        this.errInfo = "";
        this.timeOut = timeOut;//30秒

        this.responseCode = 0;
        this.charset = charset;

        this.inputStream = null;
    }

    /**
     * 设置�?书信�?�
     *
     * @param certFile   �?书文件
     * @param certPasswd �?书密�?
     */
    public void setCertInfo(File certFile, String certPasswd) {
        this.certFile = certFile;
        this.certPasswd = certPasswd;
    }

    /**
     * 设置ca
     *
     * @param caFile
     */
    public void setCaInfo(File caFile) {
        this.caFile = caFile;
    }

    /**
     * 设置请求内容
     *
     * @param reqContent 表求内容
     */
    public void setReqContent(String reqContent) {
        this.reqContent = reqContent;
    }

    /**
     * 获�?�结果内容
     *
     * @return String
     * @throws IOException
     */
    public String getResContent() {
        try {
            this.doResponse();
        } catch (IOException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
            //return "";
        }

        return this.resContent;
    }

    /**
     * 设置请求方法post或者get
     *
     * @param method 请求方法post/get
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获�?�错误信�?�
     *
     * @return String
     */
    public String getErrInfo() {
        return this.errInfo;
    }

    /**
     * 设置超时时间,以秒为�?��?
     *
     * @param timeOut 超时时间,以秒为�?��?
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 获�?�http状�?�?
     *
     * @return int
     */
    public int getResponseCode() {
        return this.responseCode;
    }

    /**
     * 执行http调用。true:�?功 false:失败
     *
     * @return boolean
     */
    public boolean call() {

        boolean isRet = false;

        //http
        if (null == this.caFile && null == this.certFile) {
            try {
                this.callHttp();
                isRet = true;
            } catch (IOException e) {
                _log.error("", e);
                this.errInfo = e.getMessage();
            } catch (Exception e) {
                _log.error("", e);
                this.errInfo = e.getMessage();
            }
            return isRet;
        }

        //https
        return calls();

    }

    public boolean calls() {

        boolean isRet = false;

        //https
        try {
            this.callHttps();
            isRet = true;
        } catch (UnrecoverableKeyException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (KeyManagementException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (CertificateException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (KeyStoreException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (IOException e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        } catch (Exception e) {
            _log.error("", e);
            this.errInfo = e.getMessage();
        }
        return isRet;

    }

    protected void callHttp() throws IOException {

        if ("POST".equals(this.method.toUpperCase())) {
            String url = HttpClientUtil.getURL(this.reqContent);
            String queryString = HttpClientUtil.getQueryString(this.reqContent);
            byte[] postData = queryString.getBytes(this.charset);
            this.httpPostMethod(url, postData);

            return;
        }

        this.httpGetMethod(this.reqContent);

    }

    protected void callHttps() throws IOException, CertificateException,
            KeyStoreException, NoSuchAlgorithmException,
            UnrecoverableKeyException, KeyManagementException {

        // ca目录
        /*String caPath = this.caFile.getParent();

        File jksCAFile = new File(caPath + "/"
                + HttpClient.JKS_CA_FILENAME);
        if (!jksCAFile.isFile()) {
            X509Certificate cert = (X509Certificate) HttpClientUtil
                    .getCertificate(this.caFile);

            FileOutputStream out = new FileOutputStream(jksCAFile);

            // store jks file
            HttpClientUtil.storeCACert(cert, HttpClient.JKS_CA_ALIAS,
                    HttpClient.JKS_CA_PASSWORD, out);

            out.close();

        }

        FileInputStream trustStream = new FileInputStream(jksCAFile);
        FileInputStream keyStream = new FileInputStream(this.certFile);*/

		/*SSLContext sslContext = HttpClientUtil.getSSLContext(trustStream,
                HttpClient.JKS_CA_PASSWORD, keyStream, this.certPasswd);*/

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()},
                new java.security.SecureRandom());

        //关闭�?
        //keyStream.close();
        //trustStream.close();

        if ("POST".equals(this.method.toUpperCase())) {
            String url = HttpClientUtil.getURL(this.reqContent);
            String queryString = HttpClientUtil.getQueryString(this.reqContent);
            byte[] postData = queryString.getBytes(this.charset);

            this.httpsPostMethod(url, postData, sslContext);

            return;
        }

        this.httpsGetMethod(this.reqContent, sslContext);

    }

    /**
     * 以http post方�?通信
     *
     * @param url
     * @param postData
     * @throws IOException
     */
    protected void httpPostMethod(String url, byte[] postData)
            throws IOException {

        HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);

        this.doPost(conn, postData);
    }

    /**
     * 以http get方�?通信
     *
     * @param url
     * @throws IOException
     */
    protected void httpGetMethod(String url) throws IOException {

        HttpURLConnection httpConnection =
                HttpClientUtil.getHttpURLConnection(url);

        this.setHttpRequest(httpConnection);

        httpConnection.setRequestMethod("GET");

        this.responseCode = httpConnection.getResponseCode();

        this.inputStream = httpConnection.getInputStream();

    }

    /**
     * 以https get方�?通信
     *
     * @param url
     * @param sslContext
     * @throws IOException
     */
    protected void httpsGetMethod(String url, SSLContext sslContext)
            throws IOException {

        SSLSocketFactory sf = sslContext.getSocketFactory();

        HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

        conn.setSSLSocketFactory(sf);

        this.doGet(conn);

    }

    protected void httpsPostMethod(String url, byte[] postData,
                                   SSLContext sslContext) throws IOException {

        SSLSocketFactory sf = sslContext.getSocketFactory();

        HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

        conn.setSSLSocketFactory(sf);

        this.doPost(conn, postData);

    }

    /**
     * 设置http请求默认属性
     *
     * @param httpConnection
     */
    protected void setHttpRequest(HttpURLConnection httpConnection) {

        //设置连接超时时间
        httpConnection.setConnectTimeout(this.timeOut * 1000);

        //User-Agent
        httpConnection.setRequestProperty("User-Agent",
                HttpClient.USER_AGENT_VALUE);

        //�?使用缓存
        httpConnection.setUseCaches(false);

        //�?许输入输出
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);

    }

    /**
     * 处�?�应答
     *
     * @throws IOException
     */
    protected void doResponse() throws IOException {

        if (null == this.inputStream) {
            return;
        }

        //获�?�应答内容
        this.resContent = HttpClientUtil.InputStreamTOString(this.inputStream, this.charset);

        //关闭输入�?
        this.inputStream.close();

    }

    /**
     * post方�?处�?�
     *
     * @param conn
     * @param postData
     * @throws IOException
     */
    protected void doPost(HttpURLConnection conn, byte[] postData)
            throws IOException {

        // 以post方�?通信
        conn.setRequestMethod("POST");

        // 设置请求默认属性
        this.setHttpRequest(conn);

        // Content-Type
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");


        BufferedOutputStream out = new BufferedOutputStream(conn
                .getOutputStream());

        final int len = 1024; // 1KB
        HttpClientUtil.doOutput(out, postData, len);



        /*PrintWriter out = new PrintWriter(conn.getOutputStream());
        // �?��?请求�?�数
        out.print(new String(postData));
        // flush输出�?的缓冲
        out.flush();*/


        // 关闭�?
        out.close();

        // 获�?��?应返回状�?�?
        this.responseCode = conn.getResponseCode();

        // 获�?�应答输入�?
        this.inputStream = conn.getInputStream();

    }

    /**
     * get方�?处�?�
     *
     * @param conn
     * @throws IOException
     */
    protected void doGet(HttpURLConnection conn) throws IOException {

        //以GET方�?通信
        conn.setRequestMethod("GET");

        //设置请求默认属性
        this.setHttpRequest(conn);

        //获�?��?应返回状�?�?
        this.responseCode = conn.getResponseCode();

        //获�?�应答输入�?
        this.inputStream = conn.getInputStream();
    }

    public static String callHttpPost(String url) {
        return callHttpPost(url, 60); // 默认超时时间60秒
    }

    public static String callHttpPost(String url, int connect_timeout) {
        return callHttpPost(url, connect_timeout, "UTF-8"); // 默认编�? UTF-8
    }

    public static String callHttpPost(String url, int connect_timeout, String encode) {
        HttpClient client = new HttpClient(url, "POST", connect_timeout, encode);
        client.call();
        return client.getResContent();
    }

    public static String callHttpsPost(String url) {

        HttpClient client = new HttpClient(url, "POST", 60, "UTF-8");
        client.calls();
        return client.getResContent();

    }


    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

}
