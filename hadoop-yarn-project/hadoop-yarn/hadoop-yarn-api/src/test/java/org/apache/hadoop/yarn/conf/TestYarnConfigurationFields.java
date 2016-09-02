/**
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

package org.apache.hadoop.yarn.conf;

import java.util.HashSet;

import org.apache.hadoop.conf.TestConfigurationFieldsBase;

/**
 * Unit test class to compare
 * {@link org.apache.hadoop.yarn.conf.YarnConfiguration} and
 * yarn-default.xml for missing properties.  Currently only throws an error
 * if the class is missing a property.
 * <p></p>
 * Refer to {@link org.apache.hadoop.conf.TestConfigurationFieldsBase}
 * for how this class works.
 */
public class TestYarnConfigurationFields extends TestConfigurationFieldsBase {

  @SuppressWarnings("deprecation")
  @Override
  public void initializeMemberVariables() {
    xmlFilename = new String("yarn-default.xml");
    configurationClasses = new Class[] { YarnConfiguration.class };

    // Allocate for usage
    configurationPropsToSkipCompare = new HashSet<String>();
    configurationPrefixToSkipCompare = new HashSet<String>();

    // Set error modes
    errorIfMissingConfigProps = true;
    errorIfMissingXmlProps = true;

    // Specific properties to skip
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_APPLICATIONCLIENT_PROTOCOL);
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_APPLICATIONMASTER_PROTOCOL);
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_CONTAINER_MANAGEMENT_PROTOCOL);
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_RESOURCE_LOCALIZER);
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_RESOURCEMANAGER_ADMINISTRATION_PROTOCOL);
    configurationPropsToSkipCompare
        .add(YarnConfiguration
            .YARN_SECURITY_SERVICE_AUTHORIZATION_RESOURCETRACKER_PROTOCOL);
    configurationPropsToSkipCompare.add(YarnConfiguration.CURATOR_LEADER_ELECTOR);

    // Federation default configs to be ignored
    configurationPropsToSkipCompare
        .add(YarnConfiguration.DEFAULT_FEDERATION_STATESTORE_CLIENT_CLASS);
    configurationPropsToSkipCompare
        .add(YarnConfiguration.FEDERATION_FAILOVER_ENABLED);
    configurationPropsToSkipCompare
        .add(YarnConfiguration.FEDERATION_STATESTORE_HEARTBEAT_INTERVAL_SECS);
    configurationPropsToSkipCompare
        .add(YarnConfiguration.RM_EPOCH);

    // Ignore blacklisting nodes for AM failures feature since it is still a
    // "work in progress"
    configurationPropsToSkipCompare.add(YarnConfiguration.
        AM_SCHEDULING_NODE_BLACKLISTING_ENABLED);
    configurationPropsToSkipCompare.add(YarnConfiguration.
        AM_SCHEDULING_NODE_BLACKLISTING_DISABLE_THRESHOLD);

    // Ignore all YARN Application Timeline Service (version 1) properties
    configurationPrefixToSkipCompare.add("yarn.timeline-service.");
    // skip deprecated RM_SYSTEM_METRICS_PUBLISHER_ENABLED
    configurationPropsToSkipCompare
        .add(YarnConfiguration.RM_SYSTEM_METRICS_PUBLISHER_ENABLED);

    // Used as Java command line properties, not XML
    configurationPrefixToSkipCompare.add("yarn.app.container");

    // Ignore NodeManager "work in progress" variables
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_NETWORK_RESOURCE_ENABLED);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_NETWORK_RESOURCE_INTERFACE);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_NETWORK_RESOURCE_OUTBOUND_BANDWIDTH_MBIT);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_NETWORK_RESOURCE_OUTBOUND_BANDWIDTH_YARN_MBIT);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_DISK_RESOURCE_ENABLED);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_MEMORY_RESOURCE_PREFIX);
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.NM_CPU_RESOURCE_ENABLED);

    // Set by container-executor.cfg
    configurationPrefixToSkipCompare.add(YarnConfiguration.NM_USER_HOME_DIR);

    // Ignore deprecated properties
    configurationPrefixToSkipCompare
        .add(YarnConfiguration.YARN_CLIENT_APP_SUBMISSION_POLL_INTERVAL_MS);

    // Allocate for usage
    xmlPropsToSkipCompare = new HashSet<String>();
    xmlPrefixToSkipCompare = new HashSet<String>();

    // Possibly obsolete, but unable to verify 100%
    xmlPropsToSkipCompare.add("yarn.nodemanager.aux-services.mapreduce_shuffle.class");
    xmlPropsToSkipCompare.add("yarn.resourcemanager.container.liveness-monitor.interval-ms");

    // Used in the XML file as a variable reference internal to the XML file
    xmlPropsToSkipCompare.add("yarn.nodemanager.hostname");

    // Ignore all YARN Application Timeline Service (version 1) properties
    xmlPrefixToSkipCompare.add("yarn.timeline-service");

    // Currently defined in RegistryConstants/core-site.xml
    xmlPrefixToSkipCompare.add("hadoop.registry");

    // Add the filters used for checking for collision of default values.
    initDefaultValueCollisionCheck();
  }

  /**
   * Add filters used to perform the check of default values collision by
   * {@link TestConfigurationFieldsBase#filtersForDefaultValueCollisionCheck}.
   */
  private void initDefaultValueCollisionCheck() {
    filtersForDefaultValueCollisionCheck.add("_PORT");
  }
}
