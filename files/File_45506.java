/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.common.utils;

import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ç½‘ç»œæ“?ä½œå·¥å…·ç±»
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class NetUtils {

    /**
     * slf4j Logger for this class
     */
    private final static Logger LOGGER   = LoggerFactory.getLogger(NetUtils.class);

    /**
     * æœ€å°?ç«¯å?£
     */
    private static final int    MIN_PORT = 0;
    /**
     * æœ€å¤§ç«¯å?£
     */
    private static final int    MAX_PORT = 65535;

    /**
     * åˆ¤æ–­ç«¯å?£æ˜¯å?¦æœ‰æ•ˆ 0-65535
     *
     * @param port ç«¯å?£
     * @return æ˜¯å?¦æœ‰æ•ˆ
     */
    public static boolean isInvalidPort(int port) {
        return port > MAX_PORT || port < MIN_PORT;
    }

    /**
     * åˆ¤æ–­ç«¯å?£æ˜¯å?¦éš?æœºç«¯å?£ å°?äºŽ0è¡¨ç¤ºéš?æœº
     *
     * @param port ç«¯å?£
     * @return æ˜¯å?¦éš?æœºç«¯å?£
     */
    public static boolean isRandomPort(int port) {
        return port == -1;
    }

    /**
     * æ£€æŸ¥å½“å‰?æŒ‡å®šç«¯å?£æ˜¯å?¦å?¯ç”¨ï¼Œä¸?å?¯ç”¨åˆ™è‡ªåŠ¨+1å†?è¯•ï¼ˆéš?æœºç«¯å?£ä»Žé»˜è®¤ç«¯å?£å¼€å§‹æ£€æŸ¥ï¼‰
     *
     * @param host å½“å‰?ipåœ°å?€
     * @param port å½“å‰?æŒ‡å®šç«¯å?£
     * @return ä»ŽæŒ‡å®šç«¯å?£å¼€å§‹å?Žç¬¬ä¸€ä¸ªå?¯ç”¨çš„ç«¯å?£
     */
    public static int getAvailablePort(String host, int port) {
        return getAvailablePort(host, port, MAX_PORT);
    }

    /**
     * æ£€æŸ¥å½“å‰?æŒ‡å®šç«¯å?£æ˜¯å?¦å?¯ç”¨ï¼Œä¸?å?¯ç”¨åˆ™è‡ªåŠ¨+1å†?è¯•ï¼ˆéš?æœºç«¯å?£ä»Žé»˜è®¤ç«¯å?£å¼€å§‹æ£€æŸ¥ï¼‰
     *
     * @param host    å½“å‰?ipåœ°å?€
     * @param port    å½“å‰?æŒ‡å®šç«¯å?£
     * @param maxPort æœ€å¤§ç«¯å?£
     * @return ä»ŽæŒ‡å®šç«¯å?£å¼€å§‹å?Žç¬¬ä¸€ä¸ªå?¯ç”¨çš„ç«¯å?£
     */
    public static int getAvailablePort(String host, int port, int maxPort) {
        if (isAnyHost(host)
            || isLocalHost(host)
            || isHostInNetworkCard(host)) {
            if (port < MIN_PORT) {
                port = MIN_PORT;
            }
            for (int i = port; i <= maxPort; i++) {
                ServerSocket ss = null;
                try {
                    ss = new ServerSocket();
                    ss.bind(new InetSocketAddress(host, i));
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("ip:{} port:{} is available", host, i);
                    }
                    return i;
                } catch (IOException e) {
                    // continue
                    if (LOGGER.isWarnEnabled()) {
                        LOGGER.warn("Can't bind to address [{}:{}], " +
                            "Maybe 1) The port has been bound. " +
                            "2) The network card of this host is not exists or disable. " +
                            "3) The host is wrong.", host, i);
                    }
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("Begin try next port(auto +1):{}", i + 1);
                    }
                } finally {
                    IOUtils.closeQuietly(ss);
                }
            }
            throw new SofaRpcRuntimeException("Can't bind to ANY port of " + host + ", please check config");
        } else {
            throw new SofaRpcRuntimeException("The host " + host
                + " is not found in network cards, please check config");
        }
    }

    /**
     * ä»»æ„?åœ°å?€
     */
    public static final String   ANYHOST          = "0.0.0.0";
    /**
     * æœ¬æœºåœ°å?€æ­£åˆ™
     */
    private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");

    /**
     * IPv4åœ°å?€
     */
    public static final Pattern  IPV4_PATTERN     = Pattern
                                                      .compile(
                                                      "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    /**
     * æ˜¯å?¦æœ¬åœ°åœ°å?€ 127.x.x.x æˆ–è€… localhost
     *
     * @param host åœ°å?€
     * @return æ˜¯å?¦æœ¬åœ°åœ°å?€
     */
    public static boolean isLocalHost(String host) {
        return StringUtils.isNotBlank(host)
            && (LOCAL_IP_PATTERN.matcher(host).matches() || "localhost".equalsIgnoreCase(host));
    }

    /**
     * æ˜¯å?¦é»˜è®¤åœ°å?€ 0.0.0.0
     *
     * @param host åœ°å?€
     * @return æ˜¯å?¦é»˜è®¤åœ°å?€
     */
    public static boolean isAnyHost(String host) {
        return ANYHOST.equals(host);
    }

    /**
     * æ˜¯å?¦IPv4åœ°å?€ 0.0.0.0
     *
     * @param host åœ°å?€
     * @return æ˜¯å?¦é»˜è®¤åœ°å?€
     */
    public static boolean isIPv4Host(String host) {
        return StringUtils.isNotBlank(host)
            && IPV4_PATTERN.matcher(host).matches();
    }

    /**
     * æ˜¯å?¦é?žæ³•åœ°å?€ï¼ˆæœ¬åœ°æˆ–é»˜è®¤ï¼‰
     *
     * @param host åœ°å?€
     * @return æ˜¯å?¦é?žæ³•åœ°å?€
     */
    static boolean isInvalidLocalHost(String host) {
        return StringUtils.isBlank(host)
            || isAnyHost(host)
            || isLocalHost(host);
    }

    /**
     * æ˜¯å?¦å?ˆæ³•åœ°å?€ï¼ˆé?žæœ¬åœ°ï¼Œé?žé»˜è®¤çš„IPv4åœ°å?€ï¼‰
     *
     * @param address InetAddress
     * @return æ˜¯å?¦å?ˆæ³•
     */
    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null
            && !isAnyHost(name)
            && !isLocalHost(name)
            && isIPv4Host(name));
    }

    /**
     * æ˜¯å?¦ç½‘å?¡ä¸Šçš„åœ°å?€
     *
     * @param host åœ°å?€
     * @return æ˜¯å?¦é»˜è®¤åœ°å?€
     */
    public static boolean isHostInNetworkCard(String host) {
        try {
            InetAddress addr = InetAddress.getByName(host);
            return NetworkInterface.getByInetAddress(addr) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * å¾—åˆ°æœ¬æœºIPv4åœ°å?€
     *
     * @return ipåœ°å?€
     */
    public static String getLocalIpv4() {
        InetAddress address = getLocalAddress();
        return address == null ? null : address.getHostAddress();
    }

    /**
     * é??åŽ†æœ¬åœ°ç½‘å?¡ï¼Œè¿”å›žç¬¬ä¸€ä¸ªå?ˆç?†çš„IPï¼Œä¿?å­˜åˆ°ç¼“å­˜ä¸­
     *
     * @return æœ¬åœ°ç½‘å?¡IP
     */
    public static InetAddress getLocalAddress() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Error when retrieving ip address: " + e.getMessage(), e);
            }
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            try {
                                InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                                if (LOGGER.isWarnEnabled()) {
                                    LOGGER.warn("Error when retrieving ip address: " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        if (LOGGER.isWarnEnabled()) {
                            LOGGER.warn("Error when retrieving ip address: " + e.getMessage(), e);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Error when retrieving ip address: " + e.getMessage(), e);
            }
        }
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Can't get valid host, will use 127.0.0.1 instead.");
        }
        return localAddress;
    }

    /**
     * InetSocketAddressè½¬ host:port å­—ç¬¦ä¸²
     *
     * @param address InetSocketAddressè½¬
     * @return host:port å­—ç¬¦ä¸²
     */
    public static String toAddressString(InetSocketAddress address) {
        if (address == null) {
            return StringUtils.EMPTY;
        } else {
            return toIpString(address) + ":" + address.getPort();
        }
    }

    /**
     * å¾—åˆ°ipåœ°å?€
     *
     * @param address InetSocketAddress
     * @return ipåœ°å?€
     */
    public static String toIpString(InetSocketAddress address) {
        if (address == null) {
            return null;
        } else {
            InetAddress inetAddress = address.getAddress();
            return inetAddress == null ? address.getHostName() :
                inetAddress.getHostAddress();
        }
    }

    /**
     * æœ¬åœ°å¤šipæƒ…å†µä¸‹ã€?è¿žä¸€ä¸‹æ³¨å†Œä¸­å¿ƒåœ°å?€å¾—åˆ°æœ¬åœ°IPåœ°å?€
     *
     * @param registryIp æ³¨å†Œä¸­å¿ƒåœ°å?€
     * @return æœ¬åœ°å¤šipæƒ…å†µä¸‹å¾—åˆ°æœ¬åœ°èƒ½è¿žä¸Šæ³¨å†Œä¸­å¿ƒçš„IPåœ°å?€
     */
    public static String getLocalHostByRegistry(String registryIp) {
        String host = null;
        if (registryIp != null && registryIp.length() > 0) {
            List<InetSocketAddress> addrs = getIpListByRegistry(registryIp);
            for (int i = 0; i < addrs.size(); i++) {
                InetAddress address = getLocalHostBySocket(addrs.get(i));
                if (address != null) {
                    host = address.getHostAddress();
                    if (host != null && !NetUtils.isInvalidLocalHost(host)) {
                        return host;
                    }
                }
            }
        }
        if (NetUtils.isInvalidLocalHost(host)) {
            host = NetUtils.getLocalIpv4();
        }
        return host;
    }

    /**
     * é€šè¿‡è¿žæŽ¥è¿œç¨‹åœ°å?€å¾—åˆ°æœ¬æœºå†…ç½‘åœ°å?€
     *
     * @param remoteAddress è¿œç¨‹åœ°å?€
     * @return æœ¬æœºå†…ç½‘åœ°å?€
     */
    private static InetAddress getLocalHostBySocket(InetSocketAddress remoteAddress) {
        InetAddress host = null;
        try {
            // åŽ»è¿žä¸€ä¸‹è¿œç¨‹åœ°å?€
            Socket socket = new Socket();
            try {
                socket.connect(remoteAddress, 1000);
                // å¾—åˆ°æœ¬åœ°åœ°å?€
                host = socket.getLocalAddress();
            } finally {
                IOUtils.closeQuietly(socket);
            }
        } catch (Exception e) {
            LOGGER.warn("Can not connect to host {}, cause by :{}",
                remoteAddress.toString(), e.getMessage());
        }
        return host;
    }

    /**
     * è§£æž?æ³¨å†Œä¸­å¿ƒåœ°å?€é…?ç½®ä¸ºå¤šä¸ªè¿žæŽ¥åœ°å?€
     *
     * @param registryIp æ³¨å†Œä¸­å¿ƒåœ°å?€
     * @return å?¯ä»¥è¿žæŽ¥æ³¨å†Œä¸­å¿ƒçš„æœ¬æœºIP
     */
    public static List<InetSocketAddress> getIpListByRegistry(String registryIp) {
        List<String[]> ips = new ArrayList<String[]>();
        String defaultPort = null;

        String[] srcIps = registryIp.split(",");
        for (String add : srcIps) {
            int a = add.indexOf("://");
            if (a > -1) {
                add = add.substring(a + 3); // åŽ»æŽ‰å??è®®å¤´
            }
            String[] s1 = add.split(":");
            if (s1.length > 1) {
                if (defaultPort == null && s1[1] != null && s1[1].length() > 0) {
                    defaultPort = s1[1];
                }
                ips.add(new String[] { s1[0], s1[1] }); // å¾—åˆ°ipå’Œç«¯å?£
            } else {
                ips.add(new String[] { s1[0], defaultPort });
            }
        }

        List<InetSocketAddress> ads = new ArrayList<InetSocketAddress>();
        for (int j = 0; j < ips.size(); j++) {
            String[] ip = ips.get(j);
            try {
                InetSocketAddress address = new InetSocketAddress(ip[0],
                    Integer.parseInt(ip[1] == null ? defaultPort : ip[1]));
                ads.add(address);
            } catch (Exception ignore) { //NOPMD
            }
        }

        return ads;
    }

    /**
     * åˆ¤æ–­å½“å‰?ipæ˜¯å?¦ç¬¦å?ˆç™½å??å?•
     *
     * @param whiteList ç™½å??å?•ï¼Œå?¯ä»¥é…?ç½®ä¸º*
     * @param localIP   å½“å‰?åœ°å?€
     * @return æ˜¯å?¦åœ¨å??å?•é‡Œ
     */
    public static boolean isMatchIPByPattern(String whiteList, String localIP) {
        if (StringUtils.isNotBlank(whiteList)) {
            if (StringUtils.ALL.equals(whiteList)) {
                return true;
            }
            for (String ips : whiteList.replace(',', ';').split(";", -1)) {
                try {
                    if (ips.contains(StringUtils.ALL)) { // å¸¦é€šé…?ç¬¦
                        String regex = ips.trim().replace(".", "\\.").replace("*", ".*");
                        Pattern pattern = Pattern.compile(regex);
                        if (pattern.matcher(localIP).find()) {
                            return true;
                        }
                    } else if (!isIPv4Host(ips)) { // ä¸?å¸¦é€šé…?ç¬¦çš„æ­£åˆ™è¡¨è¾¾å¼?
                        String regex = ips.trim().replace(".", "\\.");
                        Pattern pattern = Pattern.compile(regex);
                        if (pattern.matcher(localIP).find()) {
                            return true;
                        }
                    } else {
                        if (ips.equals(localIP)) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    LOGGER.warn("syntax of pattern {} is invalid", ips);
                }
            }
        }
        return false;
    }

    /**
     * è¿žæŽ¥è½¬å­—ç¬¦ä¸²
     *
     * @param local  æœ¬åœ°åœ°å?€
     * @param remote è¿œç¨‹åœ°å?€
     * @return åœ°å?€ä¿¡æ?¯å­—ç¬¦ä¸²
     */
    public static String connectToString(InetSocketAddress local, InetSocketAddress remote) {
        return toAddressString(local) + " <-> " + toAddressString(remote);
    }

    /**
     * è¿žæŽ¥è½¬å­—ç¬¦ä¸²
     *
     * @param local1  æœ¬åœ°åœ°å?€
     * @param remote1 è¿œç¨‹åœ°å?€
     * @return åœ°å?€ä¿¡æ?¯å­—ç¬¦ä¸²
     */
    public static String channelToString(SocketAddress local1, SocketAddress remote1) {
        try {
            InetSocketAddress local = (InetSocketAddress) local1;
            InetSocketAddress remote = (InetSocketAddress) remote1;
            return toAddressString(local) + " -> " + toAddressString(remote);
        } catch (Exception e) {
            return local1 + "->" + remote1;
        }
    }

    /**
     * æ˜¯å?¦å?¯ä»¥telnet
     *
     * @param ip      è¿œç¨‹åœ°å?€
     * @param port    è¿œç¨‹ç«¯å?£
     * @param timeout è¿žæŽ¥è¶…æ—¶
     * @return æ˜¯å?¦å?¯è¿žæŽ¥
     */
    public static boolean canTelnet(String ip, int port, int timeout) {
        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            return socket.isConnected() && !socket.isClosed();
        } catch (Exception e) {
            return false;
        } finally {
            IOUtils.closeQuietly(socket);
        }
    }
}
