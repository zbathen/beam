/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.extensions.sql.meta.provider.kafka;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaTableProviderJsonIT extends KafkaTableProviderIT {
  @Override
  protected ProducerRecord<String, byte[]> generateProducerRecord(int i) {
    return new ProducerRecord<>(
        kafkaOptions.getKafkaTopic(), "k" + i, createJson(i).getBytes(UTF_8));
  }

  @Override
  protected String getPayloadFormat() {
    return "json";
  }

  private String createJson(int i) {
    return String.format(
        "{\"f_long\": %s, \"f_int\": %s, \"f_string\": \"%s\"}", i, i % 3 + 1, "value" + i);
  }
}