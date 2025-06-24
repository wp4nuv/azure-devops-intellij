// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the project root.

package com.microsoft.alm.plugin.services;

import org.jetbrains.annotations.NotNull;

/**
 * This class is a singleton that holds all of the services that must be provided by the plugin that uses this module.
 * When the plugin is loaded for the first time, this class must be initialized. It may only be initialized once.
 * <p/>
 * Thread-safety: Not Thread Safe
 */
public class PluginServiceProvider {

    private boolean initialized = false;
    private boolean insideIDE = false;
    private ServerContextStore contextStore;
    private CredentialsPrompt credentialsPrompt;
    private DeviceFlowResponsePrompt deviceFlowResponsePrompt;
    private PropertyService propertyService;
    private LocalizationService localizationService;
    private HttpProxyService httpProxyService;
    private AsyncService asyncService;
    private CertificateService certificateService;

    private static class ProviderHolder {
        private static final PluginServiceProvider INSTANCE = new PluginServiceProvider();
    }

    public static PluginServiceProvider getInstance() {
        return ProviderHolder.INSTANCE;
    }

    public void initialize(final ServerContextStore contextStore,
                           final CredentialsPrompt credentialsPrompt,
                           final DeviceFlowResponsePrompt deviceFlowResponsePrompt,
                           final PropertyService propertyService,
                           final LocalizationService localizationService,
                           final HttpProxyService httpProxyService,
                           final AsyncService asyncService,
                           final CertificateService certificateService,
                           final boolean insideIDE) {
        if (!initialized) {
            forceInitialize(
                    contextStore,
                    credentialsPrompt,
                    deviceFlowResponsePrompt,
                    propertyService,
                    localizationService,
                    httpProxyService,
                    asyncService,
                    certificateService,
                    insideIDE);
        }
    }

    /**
     * This method is to be called only from tests.
     */
    public void forceInitialize(
            ServerContextStore contextStore,
            CredentialsPrompt credentialsPrompt,
            DeviceFlowResponsePrompt deviceFlowResponsePrompt,
            PropertyService propertyService,
            LocalizationService localizationService,
            HttpProxyService httpProxyService,
            AsyncService asyncService,
            CertificateService certificateService,
            boolean insideIDE) {
        this.contextStore = contextStore;
        this.credentialsPrompt = credentialsPrompt;
        this.deviceFlowResponsePrompt = deviceFlowResponsePrompt;
        this.propertyService = propertyService;
        this.localizationService = localizationService;
        this.httpProxyService = httpProxyService;
        this.asyncService = asyncService;
        this.certificateService = certificateService;
        this.insideIDE = insideIDE;
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isInsideIDE() {
        return insideIDE;
    }

    public ServerContextStore getServerContextStore() {
        assert initialized;
        assert contextStore != null;

        return contextStore;
    }

    public CredentialsPrompt getCredentialsPrompt() {
        assert initialized;
        assert credentialsPrompt != null;

        return credentialsPrompt;
    }

    public PropertyService getPropertyService() {
        assert initialized;
        assert propertyService != null;

        return propertyService;
    }

    public LocalizationService getLocalizationService() {
        assert initialized;
        assert localizationService != null;

        return localizationService;
    }

    public HttpProxyService getHttpProxyService() {
        assert initialized;
        assert httpProxyService != null;

        return httpProxyService;
    }

    public DeviceFlowResponsePrompt getDeviceFlowResponsePrompt() {
        assert initialized;
        assert deviceFlowResponsePrompt != null;

        return deviceFlowResponsePrompt;
    }

    public AsyncService getAsyncService() {
        assert initialized;
        assert asyncService != null;

        return asyncService;
    }

    @NotNull
    public CertificateService getCertificateService() {
        assert initialized;
        assert certificateService != null;

        return certificateService;
    }
}
