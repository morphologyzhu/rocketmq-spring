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

package org.apache.rocketmq.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 表名，注解在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMQMessageListener {

    /**
     * 消费分组
     *
     * Consumers of the same role is required to have exactly same subscriptions and consumerGroup to correctly achieve
     * load balance. It's required and needs to be globally unique.
     *
     *
     * See <a href="http://rocketmq.apache.org/docs/core-concept/">here</a> for further discussion.
     */
    String consumerGroup();

    /**
     * 消费主体
     *
     * Topic name.
     */
    String topic();

    /**
     * 选择消息的方式
     *
     * Control how to selector message.
     *
     * @see SelectorType
     */
    SelectorType selectorType() default SelectorType.TAG;

    /**
     * 选择消息的表达式
     *
     * Control which message can be select. Grammar please see {@link SelectorType#TAG} and {@link SelectorType#SQL92}
     */
    String selectorExpression() default "*";

    /**
     * 消费模式
     *
     * Control consume mode, you can choice receive message concurrently or orderly.
     */
    ConsumeMode consumeMode() default ConsumeMode.CONCURRENTLY;

    /**
     * 消费模型
     *
     * Control message mode, if you want all subscribers receive message all message, broadcasting is a good choice.
     */
    MessageModel messageModel() default MessageModel.CLUSTERING;

    /**
     * 消费线程数
     *
     * Max consumer thread number.
     */
    int consumeThreadMax() default 64;

}