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
package com.alipay.sofa.rpc.client;

import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utils method of ProviderInfo or ProviderGroup
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public class ProviderHelper {
    /**
     * Compare two provider group, return add list and remove list
     *
     * @param oldGroup old provider group
     * @param newGroup new provider group
     * @param add      provider list need add
     * @param remove   provider list need remove
     */
    public static void compareGroup(ProviderGroup oldGroup, ProviderGroup newGroup,
                                    List<ProviderInfo> add, List<ProviderInfo> remove) {
        compareProviders(oldGroup.getProviderInfos(), newGroup.getProviderInfos(), add, remove);
    }

    /**
     * Compare two provider list, return add list and remove list
     *
     * @param oldList old Provider list
     * @param newList new provider list
     * @param add     provider list need add
     * @param remove  provider list need remove
     */
    public static void compareProviders(List<ProviderInfo> oldList, List<ProviderInfo> newList,
                                        List<ProviderInfo> add, List<ProviderInfo> remove) {
        // æ¯”è¾ƒè€?åˆ—è¡¨å’Œå½“å‰?åˆ—è¡¨
        if (CommonUtils.isEmpty(oldList)) {
            // ç©ºå?˜æˆ?é?žç©º
            if (CommonUtils.isNotEmpty(newList)) {
                add.addAll(newList);
            }
            // ç©ºåˆ°ç©ºï¼Œå¿½ç•¥
        } else {
            // é?žç©ºå?˜æˆ?ç©º
            if (CommonUtils.isEmpty(newList)) {
                remove.addAll(oldList);
            } else {
                // é?žç©ºå?˜æˆ?é?žç©ºï¼Œæ¯”è¾ƒ
                if (CommonUtils.isNotEmpty(oldList)) {
                    List<ProviderInfo> tmpList = new ArrayList<ProviderInfo>(newList);
                    // é??åŽ†è€?çš„
                    for (ProviderInfo oldProvider : oldList) {
                        if (tmpList.contains(oldProvider)) {
                            tmpList.remove(oldProvider);
                        } else {
                            // æ–°çš„æ²¡æœ‰ï¼Œè€?çš„æœ‰ï¼Œåˆ æŽ‰
                            remove.add(oldProvider);
                        }
                    }
                    add.addAll(tmpList);
                }
            }
        }
    }

    /**
     * Compare two provider group list, return add list and remove list
     *
     * @param oldGroups old provider group list
     * @param newGroups new provider group list
     * @param add      provider list need add
     * @param remove   provider list need remove
     */
    public static void compareGroups(List<ProviderGroup> oldGroups, List<ProviderGroup> newGroups,
                                     List<ProviderInfo> add,
                                     List<ProviderInfo> remove) {
        // æ¯”è¾ƒè€?åˆ—è¡¨å’Œå½“å‰?åˆ—è¡¨
        if (CommonUtils.isEmpty(oldGroups)) {
            // ç©ºå?˜æˆ?é?žç©º
            if (CommonUtils.isNotEmpty(newGroups)) {
                for (ProviderGroup newGroup : newGroups) {
                    add.addAll(newGroup.getProviderInfos());
                }
            }
            // ç©ºåˆ°ç©ºï¼Œå¿½ç•¥
        } else {
            // é?žç©ºå?˜æˆ?ç©º
            if (CommonUtils.isEmpty(newGroups)) {
                for (ProviderGroup oldGroup : oldGroups) {
                    remove.addAll(oldGroup.getProviderInfos());
                }
            } else {
                // é?žç©ºå?˜æˆ?é?žç©ºï¼Œæ¯”è¾ƒ
                if (CommonUtils.isNotEmpty(oldGroups)) {
                    Map<String, List<ProviderInfo>> oldMap = convertToMap(oldGroups);
                    Map<String, List<ProviderInfo>> mapTmp = convertToMap(newGroups);
                    // é??åŽ†æ–°çš„
                    for (Map.Entry<String, List<ProviderInfo>> oldEntry : oldMap.entrySet()) {
                        String key = oldEntry.getKey();
                        List<ProviderInfo> oldList = oldEntry.getValue();
                        if (mapTmp.containsKey(key)) {
                            // è€?çš„æœ‰ï¼Œæ–°çš„ä¹Ÿæœ‰ï¼Œæ¯”è¾ƒå?˜åŒ–çš„éƒ¨åˆ†
                            final List<ProviderInfo> newList = mapTmp.remove(key);
                            compareProviders(oldList, newList, add, remove);
                            mapTmp.remove(key);
                        } else {
                            // è€?çš„æœ‰ï¼Œæ–°çš„æ²¡æœ‰
                            remove.addAll(oldList);
                        }
                    }
                    // æ–°çš„æœ‰ï¼Œè€?çš„æ²¡æœ‰
                    for (Map.Entry<String, List<ProviderInfo>> entry : mapTmp.entrySet()) {
                        add.addAll(entry.getValue());
                    }
                }
            }
        }
    }

    private static Map<String, List<ProviderInfo>> convertToMap(List<ProviderGroup> providerGroups) {
        Map<String, List<ProviderInfo>> map = new HashMap<String, List<ProviderInfo>>(providerGroups.size());
        for (ProviderGroup providerGroup : providerGroups) {
            List<ProviderInfo> ps = map.get(providerGroup.getName());
            if (ps == null) {
                ps = new ArrayList<ProviderInfo>();
                map.put(providerGroup.getName(), ps);
            }
            ps.addAll(providerGroup.getProviderInfos());
        }
        return map;
    }

    /**
     * Is empty boolean.
     *
     * @param group the group
     * @return the boolean
     */
    public static boolean isEmpty(ProviderGroup group) {
        return group == null || group.isEmpty();
    }

    /**
     * Write provider info to url string
     * 
     * @param providerInfo Provide info
     * @return the string
     */
    public static String toUrl(ProviderInfo providerInfo) {
        String uri = providerInfo.getProtocolType() + "://" + providerInfo.getHost() + ":" + providerInfo.getPort();
        uri += StringUtils.trimToEmpty(providerInfo.getPath());
        StringBuilder sb = new StringBuilder();
        if (providerInfo.getRpcVersion() > 0) {
            sb.append("&").append(ProviderInfoAttrs.ATTR_RPC_VERSION).append("=").append(providerInfo.getRpcVersion());
        }
        if (providerInfo.getSerializationType() != null) {
            sb.append("&").append(ProviderInfoAttrs.ATTR_SERIALIZATION).append("=")
                .append(providerInfo.getSerializationType());
        }
        for (Map.Entry<String, String> entry : providerInfo.getStaticAttrs().entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        if (sb.length() > 0) {
            uri += sb.replace(0, 1, "?").toString();
        }
        return uri;
    }

    /**
     * Parse url string to ProviderInfo.
     *
     * @param url the url
     * @return ProviderInfo 
     */
    public static ProviderInfo toProviderInfo(String url) {
        ProviderInfo providerInfo = new ProviderInfo();
        providerInfo.setOriginUrl(url);
        try {
            int protocolIndex = url.indexOf("://");
            String remainUrl;
            if (protocolIndex > -1) {
                String protocol = url.substring(0, protocolIndex).toLowerCase();
                providerInfo.setProtocolType(protocol);
                remainUrl = url.substring(protocolIndex + 3);
            } else { // é»˜è®¤
                remainUrl = url;
            }

            int addressIndex = remainUrl.indexOf(StringUtils.CONTEXT_SEP);
            String address;
            if (addressIndex > -1) {
                address = remainUrl.substring(0, addressIndex);
                remainUrl = remainUrl.substring(addressIndex);
            } else {
                int itfIndex = remainUrl.indexOf('?');
                if (itfIndex > -1) {
                    address = remainUrl.substring(0, itfIndex);
                    remainUrl = remainUrl.substring(itfIndex);
                } else {
                    address = remainUrl;
                    remainUrl = "";
                }
            }
            String[] ipAndPort = address.split(":", -1); // TODO ä¸?æ”¯æŒ?ipv6
            providerInfo.setHost(ipAndPort[0]);
            if (ipAndPort.length > 1) {
                providerInfo.setPort(CommonUtils.parseInt(ipAndPort[1], providerInfo.getPort()));
            }

            // å?Žé?¢å?¯ä»¥è§£æž?remainUrlå¾—åˆ°interfaceç­‰ /xxx?a=1&b=2
            if (remainUrl.length() > 0) {
                int itfIndex = remainUrl.indexOf('?');
                if (itfIndex > -1) {
                    String itf = remainUrl.substring(0, itfIndex);
                    providerInfo.setPath(itf);
                    // å‰©ä¸‹æ˜¯params,ä¾‹å¦‚a=1&b=2
                    remainUrl = remainUrl.substring(itfIndex + 1);
                    String[] params = remainUrl.split("&", -1);
                    for (String parm : params) {
                        String[] kvpair = parm.split("=", -1);
                        if (ProviderInfoAttrs.ATTR_WEIGHT.equals(kvpair[0]) && StringUtils.isNotEmpty(kvpair[1])) {
                            int weight = CommonUtils.parseInt(kvpair[1], providerInfo.getWeight());
                            providerInfo.setWeight(weight);
                            providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_WEIGHT, String.valueOf(weight));
                        } else if (ProviderInfoAttrs.ATTR_RPC_VERSION.equals(kvpair[0]) &&
                            StringUtils.isNotEmpty(kvpair[1])) {
                            providerInfo.setRpcVersion(CommonUtils.parseInt(kvpair[1], providerInfo.getRpcVersion()));
                        } else if (ProviderInfoAttrs.ATTR_SERIALIZATION.equals(kvpair[0]) &&
                            StringUtils.isNotEmpty(kvpair[1])) {
                            providerInfo.setSerializationType(kvpair[1]);
                        } else {
                            providerInfo.getStaticAttrs().put(kvpair[0], kvpair[1]);
                        }

                    }
                } else {
                    providerInfo.setPath(remainUrl);
                }
            } else {
                providerInfo.setPath(StringUtils.EMPTY);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert url to provider, the wrong url is:" + url, e);
        }
        return providerInfo;
    }
}
