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
package org.apache.hadoop.hbase.client;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseClassTestRule;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.testclassification.ClientTests;
import org.apache.hadoop.hbase.testclassification.SmallTests;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * See HBASE-28608. Configuration tests for (non async) connections
 */
@Category({ ClientTests.class, SmallTests.class })
public class TestConnectionConfiguration {

  @ClassRule
  public static final HBaseClassTestRule CLASS_RULE =
    HBaseClassTestRule.forClass(TestConnectionConfiguration.class);

  @Test
  public void testDefaultMetaOperationTimeout() {
    Configuration conf = HBaseConfiguration.create();
    long clientOperationTimeoutMs = 1000;
    conf.setLong(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, clientOperationTimeoutMs);
    ConnectionConfiguration config = new ConnectionConfiguration(conf);
    assertEquals(clientOperationTimeoutMs, config.getOperationTimeout());
    assertEquals(clientOperationTimeoutMs, config.getMetaOperationTimeout());
  }

}
