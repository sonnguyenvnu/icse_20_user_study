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
package com.alipay.sofa.rpc.filter;

import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.AbstractInterfaceConfig;

import java.util.ArrayList;
import java.util.List;

/**
 *  规则id的�?置形�?：a，b，!c，d
 *                  表示该Filter对bean id为a，b，d的�?务生效，对bean id为c的�?务�?生效。
 *
 *  规则�??述：a，b，c      �?�对a，b，c生效，其余�?生效。
 *           !a，!b，!c   除a，b，c�?生效外，其余都生效。
 *           a，!b，!c    除b，c�?生效外，其余都生效。
 *           如果�?进行�?置，默认对所有�?务生效。
 * 
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public abstract class BeanIdMatchFilter extends Filter {

    private static final String ID_SPLIT     = ",";

    private static final String ID_EXCLUDE   = "!";

    /**
     * 拦截器id规则
     */
    private String              idRule;

    private boolean             allEffective = true;
    private List<String>        effectiveId;
    private List<String>        excludeId;

    private volatile boolean    formatComplete;
    private final Object        formatLock   = new Object();

    @Override
    public boolean needToLoad(FilterInvoker invoker) {
        AbstractInterfaceConfig config = invoker.config;
        String invokerId = config.getId();
        if (!formatComplete) {
            synchronized (formatLock) {
                if (!formatComplete) {
                    formatId(idRule);
                    formatComplete = true;
                }
            }
        }

        return isMatch(invokerId);

    }

    protected void formatId(String ruleId) {
        if (StringUtils.isBlank(ruleId)) {
            return;
        }
        String[] ids = ruleId.split(ID_SPLIT);
        List<String> effectiveId = new ArrayList<String>(ids.length);
        List<String> excludeId = new ArrayList<String>(ids.length);

        for (String id : ids) {
            if (id.startsWith(ID_EXCLUDE)) {
                excludeId.add(id.substring(1));
            } else {
                effectiveId.add(id);
            }
        }
        this.effectiveId = effectiveId;
        this.excludeId = excludeId;
        this.allEffective = false;

    }

    protected boolean isMatch(String invokerId) {

        if (allEffective) {
            return true;
        } else {
            //如果没有排除，那么�?�生效指定id，其余�?生效。
            if (excludeId.size() == 0) {
                return effectiveId.contains(invokerId);
                //如果有排除，那么除排除id外，其余都生效。
            } else {
                return !excludeId.contains(invokerId);
            }
        }

    }

    /**
     * Getter method for property <tt>idRule</tt>.
     *
     * @return property value of idRule
     */
    public String getIdRule() {
        return idRule;
    }

    /**
     * Setter method for property <tt>idRule</tt>.
     *
     * @param idRule  value to be assigned to property idRule
     */
    public void setIdRule(String idRule) {
        this.idRule = idRule;
    }
}
