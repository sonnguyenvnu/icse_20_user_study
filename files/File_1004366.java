/*
 * Copyright 2018 Qunar, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package qunar.tc.qmq.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import qunar.tc.qmq.*;
import qunar.tc.qmq.common.ClientIdProvider;
import qunar.tc.qmq.common.ClientIdProviderFactory;
import qunar.tc.qmq.common.EnvProvider;
import qunar.tc.qmq.config.NettyClientConfigManager;
import qunar.tc.qmq.consumer.handler.MessageDistributor;
import qunar.tc.qmq.consumer.pull.PullConsumerFactory;
import qunar.tc.qmq.consumer.pull.PullRegister;
import qunar.tc.qmq.netty.client.NettyClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;

/**
 * @author miao.yang susing@gmail.com
 * @date 2012-12-28
 */
public class MessageConsumerProvider implements MessageConsumer {
    private MessageDistributor distributor;

    private final PullConsumerFactory pullConsumerFactory;

    private volatile boolean inited = false;

    private ClientIdProvider clientIdProvider;
    private EnvProvider envProvider;

    private final PullRegister pullRegister;
    private String appCode;
    private String metaServer;
    private int destroyWaitInSeconds;

    private int maxSubjectLen = 100;
    private int maxConsumerGroupLen = 100;

    private boolean autoOnline = true;

    public MessageConsumerProvider() {
        this.clientIdProvider = ClientIdProviderFactory.createDefault();
        this.pullRegister = new PullRegister();
        this.pullConsumerFactory = new PullConsumerFactory(this.pullRegister);
    }

    @PostConstruct
    public void init() {
        Preconditions.checkNotNull(appCode, "appCode是应用的唯一标识");
        Preconditions.checkNotNull(metaServer, "metaServer是meta server的地�?�");

        if (inited) return;

        synchronized (this) {
            if (inited) return;

            NettyClient.getClient().start(NettyClientConfigManager.get().getDefaultClientConfig());

            String clientId = this.clientIdProvider.get();
            this.pullRegister.setDestroyWaitInSeconds(destroyWaitInSeconds);
            this.pullRegister.setMetaServer(metaServer);
            this.pullRegister.setEnvProvider(envProvider);
            this.pullRegister.setClientId(clientId);
            this.pullRegister.setAppCode(appCode);
            this.pullRegister.init();

            distributor = new MessageDistributor(pullRegister);
            distributor.setClientId(clientId);

            pullRegister.setAutoOnline(autoOnline);
            inited = true;
        }
    }

    @Override
    public ListenerHolder addListener(String subject, String consumerGroup, MessageListener listener, Executor executor) {
        return addListener(subject, consumerGroup, listener, executor, new SubscribeParam.SubscribeParamBuilder().create());
    }

    @Override
    public ListenerHolder addListener(String subject, String consumerGroup, MessageListener listener, Executor executor, SubscribeParam subscribeParam) {
        init();
        Preconditions.checkArgument(subject != null && subject.length() <= maxSubjectLen, "subject长度�?�?许超过" + maxSubjectLen + "个字符");
        Preconditions.checkArgument(consumerGroup == null || consumerGroup.length() <= maxConsumerGroupLen, "consumerGroup长度�?�?许超过" + maxConsumerGroupLen + "个字符");

        Preconditions.checkArgument(!subject.contains("${"), "请确�?subject已�?正确解�?: " + subject);
        Preconditions.checkArgument(consumerGroup == null || !consumerGroup.contains("${"), "请确�?consumerGroup已�?正确解�?: " + consumerGroup);

        if (Strings.isNullOrEmpty(consumerGroup)) {
            subscribeParam.setBroadcast(true);
        }

        if (subscribeParam.isBroadcast()) {
            consumerGroup = clientIdProvider.get();
        }

        Preconditions.checkNotNull(executor, "消费逻辑将在该线程池里执行");
        Preconditions.checkNotNull(subscribeParam, "订阅时候的�?�数需�?指定，如果使用默认�?�数的�?请使用无此�?�数的�?载");

        return distributor.addListener(subject, consumerGroup, listener, executor, subscribeParam);
    }

    @Override
    public PullConsumer getOrCreatePullConsumer(String subject, String group, boolean isBroadcast) {
        init();

        Preconditions.checkArgument(!Strings.isNullOrEmpty(subject), "subject�?能是nullOrEmpty");
        if (!isBroadcast) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(group), "�?�广播订阅时，group�?能是nullOrEmpty");
        } else {
            group = clientIdProvider.get();
        }
        return pullConsumerFactory.getOrCreateDefault(subject, group, isBroadcast);
    }

    /**
     * QMQ需�?�?个consumer实例有一个全局唯一并且�?频�?�?�更的client id
     * QMQ默认会根�?�部署的路径和hostname生�?一个唯一id，如果当�?�?行环境�?能�??供唯一id，�?�以通过ClientIdProvider定制自己的client id
     *
     * @param clientIdProvider @see DefaultClientIdProvider
     */
    public void setClientIdProvider(ClientIdProvider clientIdProvider) {
        this.clientIdProvider = clientIdProvider;
    }

    /**
     * QMQ�??供环境隔离的功能，�?许将producer的环境P和consumer的环境C绑定到一起，绑定之�?�，C环境的consumer�?�会收到P环境的producer�?�出的消�?�。
     *
     * @param envProvider 当�?client环境provider
     * @see qunar.tc.qmq.common.EnvProvider
     */
    public void setEnvProvider(EnvProvider envProvider) {
        this.envProvider = envProvider;
    }

    /**
     * 为了方便维护应用与消�?�主题之间的关系，�?个应用�??供一个唯一的标识
     *
     * @param appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 用于�?�现meta server集群的地�?�
     * 格�?: http://<meta server address>/meta/address
     *
     * @param metaServer
     */
    public void setMetaServer(String metaServer) {
        this.metaServer = metaServer;
    }

    public void setDestroyWaitInSeconds(int destroyWaitInSeconds) {
        this.destroyWaitInSeconds = destroyWaitInSeconds;
    }

    public void setAutoOnline(boolean autoOnline){
        this.autoOnline = autoOnline;
    }

    public void online() {
        this.pullRegister.online();
    }

    public void offline() {
        this.pullRegister.offline();
    }

    public void setMaxConsumerGroupLen(int maxConsumerGroupLen) {
        this.maxConsumerGroupLen = maxConsumerGroupLen;
    }

    public void setMaxSubjectLen(int maxSubjectLen) {
        this.maxSubjectLen = maxSubjectLen;
    }

    @PreDestroy
    public void destroy() {
        pullRegister.destroy();
    }
}
