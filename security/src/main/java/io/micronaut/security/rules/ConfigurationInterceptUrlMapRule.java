/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.security.rules;

import io.micronaut.security.config.InterceptUrlMapPattern;
import io.micronaut.security.config.SecurityConfiguration;
import io.micronaut.security.token.config.TokenConfiguration;

import javax.inject.Singleton;
import java.util.List;

/**
 * A security rule implementation backed by the {@link SecurityConfiguration#getInterceptUrlMap()}.
 *
 * @author Sergio del Amo
 * @since 1.0
 */
@Singleton
public class ConfigurationInterceptUrlMapRule extends InterceptUrlMapRule {

    /**
     * The order of the rule.
     */
    public static final Integer ORDER = SensitiveEndpointRule.ORDER - 100;

    private final List<InterceptUrlMapPattern> patternList;

    /**
     *
     * @param tokenConfiguration The Token Configuration
     * @param securityConfiguration The Security Configuration
     */
    public ConfigurationInterceptUrlMapRule(TokenConfiguration tokenConfiguration,
                                            SecurityConfiguration securityConfiguration) {
        super(tokenConfiguration);
        this.patternList = securityConfiguration.getInterceptUrlMap();
    }

    @Override
    protected List<InterceptUrlMapPattern> getPatternList() {
        return this.patternList;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
