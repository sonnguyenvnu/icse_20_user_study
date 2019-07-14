package com.roncoo.pay.reconciliation.utils.https;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class HttpClientUtil {


    /**
     * @param requestUrl 请求地�?�
     * @param method 请求方�?（GET�?POST）
     * @param outputStr �??交的数�?�
     * @throws Exception
     */
    public static HttpResponse httpsRequest(String requestUrl, String method, String outputStr) throws IOException {
        HttpsURLConnection connection = null;
        try {
            SSLSocketFactory ssf = null;
            try {
                // 创建SSLContext对象，并使用我们指定的信任管�?�器�?始化
                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                ssf = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException e) {
                throw new IOException("实例化SSLContext失败", e);
            } catch (NoSuchProviderException e) {
                throw new IOException("实例化SSLContext失败", e);
            } catch (KeyManagementException e) {
                throw new IOException("�?始化SSLContext失败", e);
            }

            URL url = new URL(requestUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(ssf);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            // 设置请求方�?（GET/POST）
            connection.setRequestMethod(method);
            if ("GET".equalsIgnoreCase(method)) {
                connection.connect();
            }

            // 当有数�?�需�?�??交时
            if (null != outputStr) {
                OutputStream outputStream = connection.getOutputStream();
                // 注�?编�?格�?，防止中文乱�?
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            return new HttpResponse(connection);
        } catch (IOException e) {
            if (connection != null) {
                connection.disconnect();
            }
            throw e;
        }
    }

    /**
     * @param requestUrl 请求地�?�
     * @param method 请求方�?（GET�?POST）
     * @param outputStr �??交的数�?�
     * @throws Exception
     */
    public static HttpResponse httpsRequest2(String requestUrl, String method, String outputStr) throws IOException {
        HttpsURLConnection connection = null;
        try {
            SSLSocketFactory ssf = null;
            try {
                // 创建SSLContext对象，并使用我们指定的信任管�?�器�?始化
                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                ssf = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException e) {
                throw new IOException("实例化SSLContext失败", e);
            } catch (NoSuchProviderException e) {
                throw new IOException("实例化SSLContext失败", e);
            } catch (KeyManagementException e) {
                throw new IOException("�?始化SSLContext失败", e);
            }

            URL url = new URL(null,requestUrl,new sun.net.www.protocol.https.Handler());
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(ssf);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            // 设置请求方�?（GET/POST）
            connection.setRequestMethod(method);
            if ("GET".equalsIgnoreCase(method)) {
                connection.connect();
            }

            // 当有数�?�需�?�??交时
            if (null != outputStr) {
                OutputStream outputStream = connection.getOutputStream();
                // 注�?编�?格�?，防止中文乱�?
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            return new HttpResponse(connection);
        } catch (IOException e) {
            if (connection != null) {
                connection.disconnect();
            }
            throw e;
        }
    }

}

